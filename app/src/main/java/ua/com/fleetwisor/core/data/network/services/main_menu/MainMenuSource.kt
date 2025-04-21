package ua.com.fleetwisor.core.data.network.services.main_menu

import ua.com.fleetwisor.core.data.network.services.main_menu.dto.CarReportDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import java.time.LocalDate

interface MainMenuSource {
    suspend fun getReports(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Results<List<CarReportDto>, DataError.Network>

    suspend fun getReportExcel(
        startDate: LocalDate,
        endDate: LocalDate,
    ): Results<ByteArray, DataError.Network>
}