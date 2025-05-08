package ru.tinkoff.lunch.root.presentation

sealed interface EntrypointCommand {

    object Authenticate : EntrypointCommand
    object DeleteTokens : EntrypointCommand
}
