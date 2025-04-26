package ru.tinkoff.lunch.root.presentation

internal sealed interface EntrypointCommand {

    object Authenticate : EntrypointCommand
    // todo: object GetLunches : EntrypointCommand
}
