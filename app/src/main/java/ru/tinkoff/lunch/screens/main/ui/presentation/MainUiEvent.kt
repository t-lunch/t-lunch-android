package ru.tinkoff.lunch.screens.main.ui.presentation

sealed interface MainUiEvent {
    object Refresh : MainUiEvent
    data class LunchDetailsClicked(val id: String) : MainUiEvent
}
