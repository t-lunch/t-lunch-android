package ru.tinkoff.lunch.root.sign_up.presentation

internal sealed interface SignUpEvent

internal sealed interface SignUpUiEvent : SignUpEvent {
    object SignInClicked : SignUpUiEvent
    data class EmojiClicked(val emoji: String) : SignUpUiEvent
    data class OfficeClicked(val office: String) : SignUpUiEvent
}

internal sealed interface SignUpCommandResultEvent : SignUpEvent {

}
