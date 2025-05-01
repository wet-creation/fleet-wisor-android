package ua.com.fleetwisor.features.auth.presentation.common

enum class RegisterState {
    NONE,
    SUCCESS,
    ERROR,
    CONFLICT,
    CONFLICT_PHONE,
    CONFLICT_IPN_EDR_ID,
    CONFLICT_EMAIL,
}