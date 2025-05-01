package ua.com.fleetwisor.core.domain.utils.network

sealed interface Error

sealed interface DataError: Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        BAD_REQUEST,
        NOT_FOUND,
        CONFLICT,
        UNKNOWN,
        UNAUTHORIZED,
        CLIENT_EXCEPTION,
        FORBIDDEN
    }
    enum class Local: DataError {
        FULL_STORAGE
    }

}




