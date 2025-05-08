package ru.tinkoff.lunch.screens.profile.presentation

internal sealed interface ProfileEvent

internal sealed interface ProfileUiEvent : ProfileEvent {
    object LogoutClicked : ProfileUiEvent
}

internal sealed interface ProfileCommandResultEvent : ProfileEvent {

}
