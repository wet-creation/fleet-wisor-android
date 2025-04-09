package ua.com.fleetwisor.core.domain.utils

class Log {
    companion object {
        private val buildConfig = Config
        fun d(msg: Any?) {
            if (buildConfig.isDebug)
                println("Debug: $msg")
        }
    }
}