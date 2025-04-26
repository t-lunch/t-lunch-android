package ru.tinkoff.lunch.root.sign_up.presentation

internal sealed interface SignUpEvent

internal sealed interface SignUpUiEvent : SignUpEvent {
    object SignInClicked : SignUpUiEvent
}

internal sealed interface SignUpCommandResultEvent : SignUpEvent {

}
