package ru.tinkoff.lunch.screens.lunch_details.presentation

import ru.tinkoff.lunch.network.api.events.model.LunchEvent

sealed interface LunchDetailsEvent

sealed interface LunchDetailsUiEvent : LunchDetailsEvent {
    object JoinLunch : LunchDetailsUiEvent
    data class OnTelegramClicked(val telegramId: String) : LunchDetailsUiEvent
}

internal sealed interface LunchDetailsCommandResultEvent : LunchDetailsEvent {
    data class GetLunchDetailsSuccess(val lunch: LunchEvent) : LunchDetailsCommandResultEvent
}
