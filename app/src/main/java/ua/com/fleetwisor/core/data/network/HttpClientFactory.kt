package ua.com.fleetwisor.core.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.encodedPath
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import ua.com.fleetwisor.core.data.local.LocalAuthService
import ua.com.fleetwisor.core.data.network.services.auth.dto.AuthInfoDto
import ua.com.fleetwisor.core.data.network.services.auth.dto.TokenRequest
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.features.auth.domain.models.AuthInfo

class HttpClientFactory(
    private val localAuth: LocalAuthService,
) {
    fun getClient(): HttpClient {
        return HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(
                    json = Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
            }
            defaultRequest {
                contentType(ContentType.Application.Json)
            }

            install(Auth) {
                bearer {
                    loadTokens {
                        val info = localAuth.getAuthInfo().first()
                        BearerTokens(
                            accessToken = info.accessToken,
                            refreshToken = info.refreshToken
                        )
                    }
                    refreshTokens {
                        val info = localAuth.getAuthInfo().first()
                        val response = client.post<TokenRequest, AuthInfoDto, Unit>(
                            route = "/api/v1/auth/update/access",
                            body = TokenRequest(
                                jwtRefreshToken = info.refreshToken
                            )
                        )

                        if (response is FullResult.Success) {
                            val newAuthInfo = AuthInfo(
                                accessToken = response.data.jwtAccessToken,
                                refreshToken = info.refreshToken,
                            )
                            localAuth.saveAuthInfo(newAuthInfo)

                            BearerTokens(
                                accessToken = newAuthInfo.accessToken,
                                refreshToken = newAuthInfo.refreshToken
                            )
                        } else {
                            BearerTokens(
                                accessToken = "",
                                refreshToken = ""
                            )
                        }

                    }
                    sendWithoutRequest { request ->
                        request.url.encodedPath != "/api/v1/auth/update/access" &&
                                request.url.encodedPath != "/api/v1/auth/update/refresh" &&
                                request.url.encodedPath != "/api/v1/auth/login" &&
                                request.url.encodedPath != "/api/v1/auth/register"
                    }

                }

            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}