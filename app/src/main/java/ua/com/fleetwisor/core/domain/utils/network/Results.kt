package ua.com.fleetwisor.core.domain.utils.network


typealias RootError = Error

sealed interface FullResult<out D, out E : RootError, out EB> {
    data class Success<out D, out E : RootError, out EB>(val data: D) :
        FullResult<D, E, EB>

    data class Error<out D, out E : RootError, out EB>(val error: E, val body: EB? = null) :
        FullResult<D, E, EB>
}

inline fun <T, E : RootError, EB, R> FullResult<T, E, EB>.mapData(map: (T?) -> R): FullResult<R, E, EB> {
    return when (this) {
        is FullResult.Error -> FullResult.Error(error, body)
        is FullResult.Success -> FullResult.Success(map(data))
    }
}

inline fun <T, E : RootError, EB, ER> FullResult<T, E, EB>.mapError(map: (EB?) -> ER): FullResult<T, E, ER> {
    return when (this) {
        is FullResult.Error -> FullResult.Error(error, map(body))
        is FullResult.Success -> FullResult.Success(data)
    }
}

fun <T, E : RootError, EB> FullResult<T, E, EB>.asEmptyDataAndErrorResult(): EmptyDataAndErrorResult<E> {
    return mapError { }.mapData { }
}

typealias EmptyDataAndErrorResult<E> = FullResult<Unit, E, Unit>

typealias EmptyDataWithErrorResult<E, EB> = FullResult<Unit, E, EB>

typealias Results<D, E> = FullResult<D, E, Unit>
