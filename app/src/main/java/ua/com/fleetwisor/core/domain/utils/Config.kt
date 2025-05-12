package ua.com.fleetwisor.core.domain.utils

import ua.com.fleetwisor.BuildConfig

object Config {
    var isDebug: Boolean = BuildConfig.DEBUG
    var applicationId: String = BuildConfig.APPLICATION_ID
    var buildType: String = BuildConfig.BUILD_TYPE
    var versionCode: Int = BuildConfig.VERSION_CODE
    var versionName: String = BuildConfig.VERSION_NAME
    var baseUrl: String = BuildConfig.URL
    var locale: String = "uk"
}