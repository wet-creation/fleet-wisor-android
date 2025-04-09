package ua.com.fleetwisor.core.presentation.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ua.com.fleetwisor.R
import ua.com.fleetwisor.core.domain.utils.network.DataError
import ua.com.fleetwisor.core.domain.utils.network.FullResult
import ua.com.fleetwisor.core.presentation.ui.utils.UiText.DynamicString
import ua.com.fleetwisor.core.presentation.ui.utils.UiText.StringResource

val emptyUiText = DynamicString("")

sealed class UiText {
    data class DynamicString(val value: String) : UiText()
    class StringResource(
        @StringRes val id: Int,
        val args: Array<Any> = arrayOf()
    ) : UiText()

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> stringResource(id, *args)
        }
    }

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(id, *args)
        }
    }
}


fun DataError.asUiText(): UiText {
    return when (this) {
        DataError.Network.REQUEST_TIMEOUT -> StringResource(
            R.string.the_request_timed_out
        )

        DataError.Network.NO_INTERNET -> StringResource(
            R.string.no_internet
        )

        DataError.Network.PAYLOAD_TOO_LARGE -> StringResource(
            R.string.file_too_large
        )

        DataError.Network.SERVER_ERROR -> StringResource(
            R.string.server_error
        )

        DataError.Network.CONFLICT -> StringResource(
            R.string.conflict_error
        )

        DataError.Network.SERIALIZATION -> StringResource(
            R.string.error_serialization
        )

        DataError.Network.BAD_REQUEST -> StringResource(
            R.string.bad_request
        )

        DataError.Network.NOT_FOUND -> StringResource(
            R.string.not_found
        )

        DataError.Network.UNAUTHORIZED -> StringResource(
            R.string.please_login
        )

        else -> StringResource(R.string.unknown_error)
    }
}

fun FullResult.Error<*, DataError, Unit>.asErrorUiText(): UiText {
    return error.asUiText()
}
