package ua.com.fleetwisor.core.data.network.services.main_menu

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.main_menu.dto.CarReportDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import java.time.LocalDate

private const val getReports = "/api/v1/reports"
private const val getReportsExcel = "/api/v1/reports/excel"

class MainMenuSourceImpl(
    private val httpClientFactory: HttpClientFactory
) : MainMenuSource {
    override suspend fun getReports(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<List<CarReportDto>, DataError.Network> {
        return httpClientFactory.getClient().get<List<CarReportDto>, Unit>(
            getReports,
            queryParameters = mapOf(
                "startDate" to startDate.toString(),
                "endDate" to  endDate.toString(),
            )
        )
    }

    override suspend fun getReportExcel(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<ByteArray, DataError.Network> {
        return httpClientFactory.getClient().get<ByteArray, Unit>(
            getReportsExcel,
            queryParameters = mapOf(
                "startDate" to startDate.toString(),
                "endDate" to  endDate.toString(),
            )
        )
    }
}