package ua.com.fleetwisor.features.cars.presentation.fill_up.list

import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody
import ua.com.fleetwisor.features.cars.domain.models.FillUp

data class FilUpListState(
    val fillUps: List<FillUp> = listOf(
        FillUp(
            id = -1,
            time = "12.09.2025",
            price = 1234.0,
            checkUrl = "",
            car = Car(
                id = 0,
                brandName = "Toyota",
                color = "Чорна",
                vin = "",
                model = "Camry",
                licensePlate = "АІ 4523 КН",
                mileAge = 20340,
                fuelTypes = listOf(),
                carBody = CarBody()
            )
        )
    )
)