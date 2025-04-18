package ua.com.fleetwisor.core.data.network


import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.SerializationException
import okio.IOException
import ua.com.fleetwisor.core.domain.utils.Config
import ua.com.fleetwisor.core.domain.utils.Log
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import kotlin.collections.forEach
import kotlin.let
import kotlin.text.contains
import kotlin.text.isNotBlank
import kotlin.text.startsWith


fun constructRoute(route: String): String {
    val buildConfig = Config
    return when {
        route.contains(buildConfig.baseUrl) -> route
        route.startsWith("/") -> "${buildConfig.baseUrl}$route"
        else -> "${buildConfig.baseUrl}/$route"
    }
}

suspend inline fun <reified T, reified EB> responseToResult(response: HttpResponse): FullResult<T, DataError.Network, EB> {
    return when (val code = response.status.value) {
        in 200..299 -> FullResult.Success(response.body())
        else -> {
            val body = if (response.bodyAsText().isNotBlank()) response.body<EB>() else null
            errorHandling(code, body)
        }
    }
}

inline fun <reified T, reified EB> errorHandling(
    code: Int,
    body: EB?
): FullResult<T, DataError.Network, EB> {

    val emptyBody = EB::class != Unit::class
    val errorBody = if (emptyBody) body else null

    return when (code) {
        400 -> FullResult.Error(DataError.Network.BAD_REQUEST, errorBody)
        401 -> FullResult.Error(DataError.Network.UNAUTHORIZED, errorBody)
        403 -> FullResult.Error(DataError.Network.FORBIDDEN, errorBody)
        404 -> FullResult.Error(DataError.Network.NOT_FOUND, errorBody)
        408 -> FullResult.Error(DataError.Network.REQUEST_TIMEOUT, errorBody)
        409 -> FullResult.Error(DataError.Network.CONFLICT, errorBody)
        413 -> FullResult.Error(DataError.Network.PAYLOAD_TOO_LARGE, errorBody)
        418 -> FullResult.Error(DataError.Network.CLIENT_EXCEPTION, errorBody)
        429 -> FullResult.Error(DataError.Network.TOO_MANY_REQUESTS, errorBody)
        in 500..599 -> FullResult.Error(DataError.Network.SERVER_ERROR, errorBody)
        else -> FullResult.Error(DataError.Network.UNKNOWN, errorBody)
    }
}

suspend inline fun <reified Response : Any, reified Error> HttpClient.get(
    route: String,
    queryParameters: Map<String, Any?> = mapOf()
): FullResult<Response, DataError.Network, Error> {
    return safeCall {
        get {
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified Request, reified Response : Any, reified Error> HttpClient.post(
    route: String,
    body: Request,
    queryParameters: Map<String, Any?> = mapOf()
): FullResult<Response, DataError.Network, Error> {
    return safeCall {
        post {
            url(constructRoute(route))
            if (body != null)
                setBody(body)
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
            contentType(ContentType.Application.Json)
        }
    }
}

suspend inline fun <reified Request, reified Response : Any, reified Error> HttpClient.put(
    route: String,
    body: Request? = null,
    queryParameters: Map<String, Any?> = mapOf()
): FullResult<Response, DataError.Network, Error> {
    return safeCall {
        put {
            url(constructRoute(route))
            body?.let {
                setBody(body)
            }
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
            contentType(ContentType.Application.Json)

        }
    }
}

suspend inline fun <reified Request, reified Response : Any, reified Error> HttpClient.delete(
    route: String,
    body: Request? = null,
    queryParameters: Map<String, Any?> = mapOf()
): FullResult<Response, DataError.Network, Error> {
    return safeCall {
        delete {
            body?.let {
                setBody(it)
            }
            url(constructRoute(route))
            queryParameters.forEach { (key, value) ->
                parameter(key, value)
            }
        }
    }
}

suspend inline fun <reified T, reified E> safeCall(execute: () -> HttpResponse): FullResult<T, DataError.Network, E> {
    val response = try {
        execute()
    } catch (e: IOException) {
        e.printStackTrace()
        return FullResult.Error(DataError.Network.NO_INTERNET)
    } catch (e: SerializationException) {
        e.printStackTrace()
        return FullResult.Error(DataError.Network.SERIALIZATION)
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        return FullResult.Error(DataError.Network.UNKNOWN)
    }

    Log.d(response.body())
    Log.d(response.headers.get("Authorization"))
    Log.d(response.status.value.toString())
    Log.d(response.bodyAsText())
    return responseToResult(response)
}

