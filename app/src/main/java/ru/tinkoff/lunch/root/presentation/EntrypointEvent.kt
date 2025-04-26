package ru.tinkoff.lunch.root.presentation

internal sealed interface EntrypointEvent

internal sealed interface EntrypointCommandResultEvent : EntrypointEvent {

    object AuthenticationPassed : EntrypointCommandResultEvent
    object AuthenticationFailed : EntrypointCommandResultEvent
}
