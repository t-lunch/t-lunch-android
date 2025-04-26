package ru.tinkoff.lunch.root.sign_in.presentation

internal sealed interface SignInNews {
    object OpenSignUp : SignInNews
    object OpenMainScreen : SignInNews
}
