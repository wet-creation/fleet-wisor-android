package ua.com.fleetwisor.features.main_menu.data

import ua.com.fleetwisor.core.data.network.services.main_menu.MainMenuSource
import ua.com.fleetwisor.core.data.network.services.main_menu.dto.CarReportDto
import ua.com.fleetwisor.core.domain.utils.Log
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.core.domain.utils.network.mapData
import ua.com.fleetwisor.features.main_menu.domain.MainMenuRepository
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport
import java.time.LocalDate

class MainMenuRepositoryImpl(
    private val mainMenuSource: MainMenuSource
) : MainMenuRepository {
    override suspend fun getReports(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<List<CarReport>, DataError.Network> {
        val res = mainMenuSource.getReports(startDate, endDate)
            .mapData { it?.map { it.asCarReport() } ?: emptyList() }
        return res
    }
}

fun CarReportDto.asCarReport() = CarReport(
    id = id,
    color = color,
    brandName = brandName,
    model = model,
    fillUpCount = fillUpCount,
    totalFillUp = totalFillUp,
    maintenanceCount = maintenanceCount,
    totalMaintenance = totalMaintenance
)