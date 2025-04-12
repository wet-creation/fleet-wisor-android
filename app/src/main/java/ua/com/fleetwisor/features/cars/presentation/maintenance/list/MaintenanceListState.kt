package ua.com.fleetwisor.features.cars.presentation.maintenance.list

import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.Maintenance

data class MaintenanceListState(
    val maintenances: List<Maintenance> = listOf(
        Maintenance(
            id = -1,
            time = "12.09.2025",
            description = "",
            checkUrl = "",
            car =   Car(
                id = 0,
                brandName = "Toyota",
                color = "Чорна",
                vin = "",
                model = "Camry",
                licensePlate = "АІ 4523 КН",
                mileAge = 20340,
                fuelTypes = listOf(),
                carBody = CarBody()
            ),
            price = 1267.02
        )
    )
)