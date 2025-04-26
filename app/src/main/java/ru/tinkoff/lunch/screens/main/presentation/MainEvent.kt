package ru.tinkoff.lunch.screens.main.presentation

internal sealed interface MainEvent

internal sealed interface MainUiEvent : MainEvent {

}

internal sealed interface MainCommandResultEvent : MainEvent {

}
