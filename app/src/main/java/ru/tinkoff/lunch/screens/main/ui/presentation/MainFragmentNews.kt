package ru.tinkoff.lunch.screens.main.ui.presentation

sealed interface MainFragmentNews {
    data class ShowError(val error: Throwable) : MainFragmentNews
    object OpenCreateLunchScreen : MainFragmentNews
    data class OpenLunchDetailsScreen(val id: String) : MainFragmentNews
}
