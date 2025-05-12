package ru.tinkoff.lunch.screens.main.presentation

import ru.tinkoff.lunch.network.api.events.model.LunchEvent

internal sealed interface MainEvent

internal sealed interface MainUiEvent : MainEvent {
    object CreateLunchClicked : MainUiEvent
}

internal sealed interface MainCommandResultEvent : MainEvent {

    data class GetLunchEventsSuccess(val events: List<LunchEvent>) : MainCommandResultEvent
    data class GetLunchEventsError(val error: Throwable) : MainCommandResultEvent
}
