package ua.com.fleetwisor.features.cars.presentation.cars.list

import ua.com.fleetwisor.features.cars.domain.models.Car
import ua.com.fleetwisor.features.cars.domain.models.CarBody

data class CarsListState(
   val cars: List<Car> = listOf(
       Car(
           id = 0,
           brandName = "Toyota",
           color = "Чорна",
           vin = "",
           model = "",
           licensePlate = "АІ 4523 КН",
           mileAge = 20340,
           fuelTypes = listOf(),
           carBody = CarBody()
       )
   )
)