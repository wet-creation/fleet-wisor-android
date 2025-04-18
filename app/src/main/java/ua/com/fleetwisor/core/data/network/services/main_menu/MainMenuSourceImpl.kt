package ua.com.fleetwisor.core.data.network.services.main_menu

import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.services.main_menu.dto.CarReportDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.Results
import java.time.LocalDate

const val getReports = "/api/v1/reports"

class MainMenuSourceImpl(
    private val httpClientFactory: HttpClientFactory
) : MainMenuSource {
    override suspend fun getReports(
        startDate: LocalDate,
        endDate: LocalDate
    ): Results<List<CarReportDto>, DataError.Network> {
        return httpClientFactory.httpClient.get<List<CarReportDto>, Unit>(
            getReports,
            queryParameters = mapOf(
                "startDate" to "2022-01-01",//startDate.toString(),
                "endDate" to "2026-01-01",// endDate.toString(),
            )
        )
    }
}