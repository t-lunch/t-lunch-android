package ru.tinkoff.lunch.root.sign_in.presentation

sealed interface SignInEvent

internal sealed interface SignInUiEvent : SignInEvent {
    object SignUpClicked : SignInUiEvent
    data class LoginClicked(
        val login: String,
        val password: String,
    ) : SignInUiEvent
}

internal sealed interface SignInCommandResultEvent : SignInEvent {
    object SignInSuccess : SignInCommandResultEvent
    data class SignInError(val message: String?) : SignInCommandResultEvent
}
