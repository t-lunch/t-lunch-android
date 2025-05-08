package ru.tinkoff.lunch.root.sign_up.presentation

sealed interface SignUpEvent

internal sealed interface SignUpUiEvent : SignUpEvent {
    object SignInClicked : SignUpUiEvent
    data class EmojiClicked(val emoji: String) : SignUpUiEvent
    data class OfficeClicked(val office: String) : SignUpUiEvent
    data class SignUpClicked(
        val login: String,
        val password: String,
        val name: String,
        val surname: String,
        val telegram: String,
    ) : SignUpUiEvent
}

internal sealed interface SignUpCommandResultEvent : SignUpEvent {
    object SignUpSuccess : SignUpCommandResultEvent
    data class SignUpError(val message: String?) : SignUpCommandResultEvent
}
