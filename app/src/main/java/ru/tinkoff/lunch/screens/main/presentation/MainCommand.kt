package ru.tinkoff.lunch.screens.main.presentation

internal sealed interface MainCommand {
    object GetLunchEvents : MainCommand
}
