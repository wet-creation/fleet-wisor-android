package ua.com.fleetwisor.core.data.network.services.car.main

import io.ktor.client.request.forms.FormPart
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import ua.com.fleetwisor.core.data.network.FilePart
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.postMultiPart
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarCreate
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarCreateWithInsurance
import ua.com.fleetwisor.core.data.network.services.car.main.dto.CarDto
import ua.com.fleetwisor.core.data.network.services.car.main.dto.InsuranceDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "/api/v1/cars"
private const val postCar = "/api/v1/cars"

class CarServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : CarService {
    override suspend fun getAll(): Results<List<CarDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }

    override suspend fun saveCar(
        value: CarCreate,
        insurance: InsuranceDto?,
        photo: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {
        val fileParts = buildList {
            if (photo != null)
                add(
                    FilePart(
                        formPart = FormPart(
                            key = "photo",
                            value = photo.second,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, photo.first)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"photo\"; filename=\"photo.jpg\""
                                )

                            }
                        )
                    )
                )
        }
        return httpClientFactory.getClient()
            .postMultiPart(postCar, CarCreateWithInsurance(value, insurance), fileParts)

    }
}