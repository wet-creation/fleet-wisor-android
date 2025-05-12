package ua.com.fleetwisor.core.data.network.services.car.fillup

import io.ktor.client.request.forms.FormPart
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import ua.com.fleetwisor.core.data.network.FilePart
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.delete
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.postMultiPart
import ua.com.fleetwisor.core.data.network.putMultiPart
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpCreate
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpDto
import ua.com.fleetwisor.core.data.network.services.car.fillup.dto.FillUpUpdate
import ua.com.fleetwisor.core.domain.utils.PathParamRoute
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "api/v1/{lang}/cars/fill-up"
private val getFillUp: PathParamRoute = { "api/v1/{lang}/cars/fill-up/$it" }
private val deleteFillUp: PathParamRoute = { "api/v1/{lang}/cars/fill-up/$it" }
private val putFillUp: PathParamRoute = { "api/v1/{lang}/cars/fill-up/$it" }
private const val postFillUp = "api/v1/{lang}/cars/fill-up"


class FillUpServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : FillUpService {
    override suspend fun getAll(): Results<List<FillUpDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }

    override suspend fun get(id: Int): Results<FillUpDto, DataError.Network> {
        return httpClientFactory.getClient().get(getFillUp(id))

    }

    override suspend fun delete(id: Int): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().delete<Unit, Unit, Unit>(deleteFillUp(id))
    }

    override suspend fun edit(
        fillUp: FillUpUpdate,
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
            .putMultiPart(putFillUp(fillUp.id), fillUp, fileParts)
    }

    override suspend fun save(
        fillUp: FillUpCreate,
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
            .postMultiPart(postFillUp, fillUp, fileParts)
    }
}