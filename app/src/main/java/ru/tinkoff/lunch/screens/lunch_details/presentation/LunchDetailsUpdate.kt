package ru.tinkoff.lunch.screens.lunch_details.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsCommandResultEvent.GetLunchDetailsSuccess

internal class LunchDetailsUpdate :
    DslUpdate<LunchDetailsState, LunchDetailsEvent, LunchDetailsCommand, LunchDetailsNews>() {

    override fun NextBuilder.update(event: LunchDetailsEvent) {
        return when (event) {
            is LunchDetailsCommandResultEvent -> handleCommandResultEvent(event)
            is LunchDetailsUiEvent -> handleUiEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: LunchDetailsUiEvent) {
        return when (event) {
            is LunchDetailsUiEvent.JoinLunch -> Unit // todo
            is LunchDetailsUiEvent.OnTelegramClicked -> {
                news(LunchDetailsNews.OpenTelegram(event.telegramId))
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: LunchDetailsCommandResultEvent) {
        return when (event) {
            is GetLunchDetailsSuccess -> {
                state { copy(lunch = event.lunch) }
            }
        }
    }
}
