package ua.com.fleetwisor.core.data.network.services.driver

import io.ktor.client.request.forms.FormPart
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import ua.com.fleetwisor.core.data.network.FilePart
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.delete
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.postMultiPart
import ua.com.fleetwisor.core.data.network.putMultiPart
import ua.com.fleetwisor.core.data.network.services.driver.dto.CreateDriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.EditDriverDto
import ua.com.fleetwisor.core.domain.utils.PathParamRoute
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results


private const val getList = "/api/v1/drivers"
private val getDriverId: PathParamRoute = { "/api/v1/drivers/$it" }
private val putDriverId: PathParamRoute = { "/api/v1/drivers/$it" }
private val deleteDriverId: PathParamRoute = { "/api/v1/drivers/$it" }
private const val postDriver = "/api/v1/drivers"

class DriverServiceImpl(
    val httpClientFactory: HttpClientFactory
) : DriverService {
    override suspend fun getList(): Results<List<DriverDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getList)
    }

    override suspend fun createDriver(
        driver: CreateDriverDto,
        frontPhoto: Pair<String, ByteArray>?,
        backPhoto: Pair<String, ByteArray>?
    ): EmptyDataAndErrorResult<DataError.Network> {

        val fileParts = buildList {
            if (frontPhoto != null)
                add(
                    FilePart(
                        formPart = FormPart(
                            key = "frontPhoto",
                            value = frontPhoto.second,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, frontPhoto.first)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\""
                                )

                            }
                        )
                    )
                )
            if (backPhoto != null)
                add(
                    FilePart(
                        formPart = FormPart(
                            key = "backPhoto",
                            value = backPhoto.second,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, backPhoto.first)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\""
                                )
                            }
                        )
                    )
                )

        }

        return httpClientFactory.getClient().postMultiPart(
            postDriver,
            driver,
            fileParts
        )
    }

    override suspend fun getDriver(id: Int): Results<DriverDto, DataError.Network> {
        return httpClientFactory.getClient().get(getDriverId(id))
    }

    override suspend fun editDriver(
        id: Int,
        editDriverDto: EditDriverDto,
        frontPhoto: Pair<String, ByteArray>?,
        backPhoto: Pair<String, ByteArray>?
    ): Results<DriverDto, DataError.Network> {
        val fileParts = buildList {
            if (frontPhoto != null)
                add(
                    FilePart(
                        formPart = FormPart(
                            key = "frontPhoto",
                            value = frontPhoto.second,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, frontPhoto.first)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\""
                                )

                            }
                        )
                    )
                )
            if (backPhoto != null)
                add(
                    FilePart(
                        formPart = FormPart(
                            key = "backPhoto",
                            value = backPhoto.second,
                            headers = Headers.build {
                                append(HttpHeaders.ContentType, backPhoto.first)
                                append(
                                    HttpHeaders.ContentDisposition,
                                    "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\""
                                )
                            }
                        )
                    )
                )

        }

        return httpClientFactory.getClient().putMultiPart(
            putDriverId(id),
            editDriverDto,
            fileParts
        )

    }

    override suspend fun deleteDriver(id: Int): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().delete<Unit, Unit, Unit>(deleteDriverId(id))
    }
}