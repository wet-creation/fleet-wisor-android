package ua.com.fleetwisor.core.data.network.services.main_menu.dto

import kotlinx.serialization.Serializable

@Serializable
data class CarReportDto(
    val id: Int,
    val color: String?,
    val brandName: String,
    val model: String?,
    val fillUpCount: Int,
    val totalFillUp: Double,
    val maintenanceCount: Int,
    val totalMaintenance: Double
)
