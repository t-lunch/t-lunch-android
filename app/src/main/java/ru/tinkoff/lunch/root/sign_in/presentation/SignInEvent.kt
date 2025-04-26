package ru.tinkoff.lunch.root.sign_in.presentation

internal sealed interface SignInEvent

internal sealed interface SignInUiEvent : SignInEvent {
    object SignUpClicked : SignInUiEvent
    object LoginClicked : SignInUiEvent
}

internal sealed interface SignInCommandResultEvent : SignInEvent {

}
