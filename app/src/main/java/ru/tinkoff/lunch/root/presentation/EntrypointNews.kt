package ru.tinkoff.lunch.root.presentation

internal sealed interface EntrypointNews {
    object OpenSignInScreen : EntrypointNews
    // todo: object OpenMainScreen : EntrypointNews
}
