package ua.com.fleetwisor.core.data.network.services.driver

import io.ktor.client.request.forms.FormPart
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import ua.com.fleetwisor.core.data.network.FilePart
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.postMultiPart
import ua.com.fleetwisor.core.data.network.services.driver.dto.CreateDriverDto
import ua.com.fleetwisor.core.data.network.services.driver.dto.DriverDto
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results


private const val getList = "/api/v1/drivers"
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
                                append(HttpHeaders.ContentDisposition, "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\"")

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
                                append(HttpHeaders.ContentDisposition, "form-data; name=\"frontPhoto\"; filename=\"photo.jpg\"")
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
}