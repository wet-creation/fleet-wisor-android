package ua.com.fleetwisor.core.domain.utils

data class SearchState<T>(
    val searchText: String = "",
    val result: List<T> = listOf()
)
