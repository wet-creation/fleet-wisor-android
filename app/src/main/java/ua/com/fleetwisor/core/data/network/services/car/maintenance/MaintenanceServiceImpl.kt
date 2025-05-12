package ua.com.fleetwisor.core.data.network.services.car.maintenance

import io.ktor.client.request.forms.FormPart
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import ua.com.fleetwisor.core.data.network.FilePart
import ua.com.fleetwisor.core.data.network.HttpClientFactory
import ua.com.fleetwisor.core.data.network.delete
import ua.com.fleetwisor.core.data.network.get
import ua.com.fleetwisor.core.data.network.postMultiPart
import ua.com.fleetwisor.core.data.network.putMultiPart
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceCreate
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceDto
import ua.com.fleetwisor.core.data.network.services.car.maintenance.dto.MaintenanceUpdate
import ua.com.fleetwisor.core.domain.utils.PathParamRoute
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.EmptyDataAndErrorResult
import ua.com.fleetwisor.core.domain.utils.network.Results

private const val getAll = "/api/v1/{lang}/cars/maintenance"
private val getById: PathParamRoute = { "/api/v1/{lang}/cars/maintenance/$it" }
private val deleteById: PathParamRoute = { "/api/v1/{lang}/cars/maintenance/$it" }
private const val post = "/api/v1/{lang}/cars/maintenance"
private val put: PathParamRoute = { "/api/v1/{lang}/cars/maintenance/$it" }

class MaintenanceServiceImpl(
    private val httpClientFactory: HttpClientFactory
) : MaintenanceService {
    override suspend fun getAll(): Results<List<MaintenanceDto>, DataError.Network> {
        return httpClientFactory.getClient().get(getAll)
    }

    override suspend fun get(id: Int): Results<MaintenanceDto, DataError.Network> {
        return httpClientFactory.getClient().get(getById(id))

    }

    override suspend fun delete(id: Int): EmptyDataAndErrorResult<DataError.Network> {
        return httpClientFactory.getClient().delete<Unit, Unit, Unit>(deleteById(id))

    }

    override suspend fun save(
        maintenanceCreate: MaintenanceCreate,
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
            .postMultiPart(post, maintenanceCreate, fileParts)
    }

    override suspend fun edit(
        edit: MaintenanceUpdate,
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
            .putMultiPart(put(edit.id), edit, fileParts)

    }
}