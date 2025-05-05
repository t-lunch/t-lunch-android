package ru.tinkoff.lunch.screens.main.presentation

internal sealed interface MainNews {
    data class ShowError(val error: Throwable) : MainNews
}
