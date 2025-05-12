package ua.com.fleetwisor.features.main_menu.presentation

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.provider.MediaStore
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.io.IOException
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.millisToLocalDate
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.presentation.ui.utils.UiText
import ua.com.fleetwisor.core.presentation.ui.utils.asErrorUiText
import ua.com.fleetwisor.features.main_menu.domain.MainMenuRepository
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport

class MainMenuViewModel(
    private val application: Application,
    private val repository: MainMenuRepository
) : AndroidViewModel(application) {
    var state by mutableStateOf(MainMenuState())
        private set


    init {
        getReports()
    }


    fun onAction(action: MainMenuAction) {
        when (action) {
            MainMenuAction.OnModalSheetClose -> {
                dismissBottomSheet()
            }

            is MainMenuAction.SearchCars -> {
                state = state.copy(
                    searchCarValue = action.searchValue,
                    filteredReports = state.reports.filter {
                        it.name.contains(action.searchValue, ignoreCase = true)
                    })

            }

            is MainMenuAction.SelectCarForReport -> {
                state =
                    state.copy(selectedReport = state.filteredReports[action.index])
                dismissBottomSheet()
            }

            is MainMenuAction.SelectPeriod -> {
                val startDate = action.start.millisToLocalDate()
                val endDate = action.end.millisToLocalDate()
                state = state.copy(
                    startDate = startDate,
                    endDate = endDate
                )
                getReports()
            }

            MainMenuAction.ShowCarSearch -> {
                state = state.copy(
                    modalBottomSheetState = ModalBottomSheetState(
                        isOpen = true,
                        showReportsList = true
                    )
                )

            }

            is MainMenuAction.DownloadExcel -> downloadReport(action.context)
        }
    }

    private fun dismissBottomSheet() {
        state = state.copy(
            modalBottomSheetState = ModalBottomSheetState(),
            filteredReports = state.reports
        )
    }

    private fun changeDownloadState(dState: DownloadState) {
        state = state.copy(downloadState = dState)

    }

    private fun downloadReport(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            changeDownloadState(DownloadState.Downloading)
            when (val res = repository.downloadReport(state.startDate, state.endDate)) {
                is FullResult.Error -> {
                    changeDownloadState(DownloadState.Error(res.asErrorUiText()))
                }

                is FullResult.Success -> {
                    try {
                        val fileName = UiText.StringResource(
                            R.string.report_file_name,
                            arrayOf(state.startDate.toString(), state.startDate.toString())
                        ).asString(context)
                        val resolver = context.contentResolver
                        val contentValues = ContentValues().apply {
                            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
                            put(
                                MediaStore.Downloads.MIME_TYPE,
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                            )
                            put(MediaStore.Downloads.IS_PENDING, 1)
                        }

                        val uri =
                            resolver.insert(
                                MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                                contentValues
                            )
                                ?: throw IOException()

                        resolver.openOutputStream(uri)?.use { outputStream ->
                            outputStream.write(res.data)
                        }
                        contentValues.clear()
                        contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
                        resolver.update(uri, contentValues, null, null)
                        changeDownloadState(DownloadState.Success)

                    } catch (_: IOException) {
                        changeDownloadState(DownloadState.Error(UiText.StringResource(R.string.download_error)))

                    }
                }
            }
            delay(1000)
            changeDownloadState(DownloadState.Idle)
        }
    }

    private fun getReports() {
        state = state.copy(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            state = when (val res = repository.getReports(state.startDate, state.endDate)) {
                is FullResult.Error -> {
                    state.copy(error = res.asErrorUiText())
                }

                is FullResult.Success -> {
                    val summary = res.data.fold(
                        CarReport(
                            brandName = UiText.StringResource(R.string.all_cars)
                                .asString(application),
                            fillUpCount = 0,
                            totalFillUp = 0.0,
                            maintenanceCount = 0,
                            totalMaintenance = 0.0
                        )
                    ) { acc, report ->
                        acc.copy(
                            fillUpCount = acc.fillUpCount + report.fillUpCount,
                            totalFillUp = acc.totalFillUp + report.totalFillUp,
                            maintenanceCount = acc.maintenanceCount + report.maintenanceCount,
                            totalMaintenance = acc.totalMaintenance + report.totalMaintenance
                        )
                    }
                    val reports = buildList {
                        add(summary)
                        addAll(res.data)
                    }
                    state.copy(
                        reports = reports,
                        selectedReport = summary,
                        filteredReports = reports
                    )
                }
            }
            state = state.copy(isLoading = false)

        }
    }

}
