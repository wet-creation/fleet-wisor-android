package ua.com.fleetwisor.features.main_menu.domain.models

data class CarReport(
    val id: Int = -1,
    val color: String? = null,
    val brandName: String? = null,
    val model: String? = null,
    val fillUpCount: Int = 0,
    val totalFillUp: Double = 0.0,
    val maintenanceCount: Int = 0,
    val totalMaintenance: Double = 0.0,
) {
    val totalSpending: Double
        get() = totalMaintenance + totalFillUp
}