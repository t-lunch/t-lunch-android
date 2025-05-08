package ru.tinkoff.lunch.root.sign_in.presentation

sealed interface SignInCommand {
    data class SignIn(
        val login: String,
        val password: String,
    ) : SignInCommand
}
