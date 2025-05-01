package ua.com.fleetwisor.features.main_menu.domain

import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import ua.com.fleetwisor.features.main_menu.domain.models.CarReport
import java.time.LocalDate

interface MainMenuRepository {
    suspend fun getReports(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<List<CarReport>, DataError.Network>


    suspend fun downloadReport(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<ByteArray, DataError.Network>
}