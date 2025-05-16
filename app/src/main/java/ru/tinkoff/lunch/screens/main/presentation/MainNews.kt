package ru.tinkoff.lunch.screens.main.presentation

internal sealed interface MainNews {
    data class ShowError(val error: Throwable) : MainNews
    object OpenCreateLunchScreen : MainNews
    data class OpenLunchDetailsScreen(val id: String) : MainNews
}
