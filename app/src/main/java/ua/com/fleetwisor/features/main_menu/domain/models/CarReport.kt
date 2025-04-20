package ua.com.fleetwisor.features.main_menu.domain.models

data class CarReport(
    val id: Int = -1,
    val color: String = "",
    val brandName: String = "",
    val model: String = "",
    val fillUpCount: Int = 0,
    val totalFillUp: Double = 0.0,
    val maintenanceCount: Int = 0,
    val totalMaintenance: Double = 0.0,
) {
    val totalSpending: Double
        get() = totalMaintenance + totalFillUp
    val name: String
        get() ="$color $brandName $model"
}