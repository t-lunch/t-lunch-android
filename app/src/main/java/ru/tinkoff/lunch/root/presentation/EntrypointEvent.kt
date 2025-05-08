package ru.tinkoff.lunch.root.presentation

sealed interface EntrypointEvent

internal sealed interface EntrypointUiEvent : EntrypointEvent {
    data class Init(val isDeepLogout: Boolean) : EntrypointUiEvent
}

internal sealed interface EntrypointCommandResultEvent : EntrypointEvent {

    object AuthenticationPassed : EntrypointCommandResultEvent
    object AuthenticationFailed : EntrypointCommandResultEvent
}
