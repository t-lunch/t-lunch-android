package ru.tinkoff.lunch.screens.main.ui.presentation

sealed interface MainUiEvent {
    data class LunchDetailsClicked(val id: String) : MainUiEvent
}
