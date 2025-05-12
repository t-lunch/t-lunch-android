package ru.tinkoff.lunch.screens.main.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate
import ru.tinkoff.lunch.common.lce.LceState
import ru.tinkoff.lunch.screens.main.presentation.MainNews.ShowError

internal class MainUpdate : DslUpdate<MainState, MainEvent, MainCommand, MainNews>() {

    override fun NextBuilder.update(event: MainEvent) {
        when (event) {
            is MainCommandResultEvent -> handleCommandResultEvent(event)
            is MainUiEvent -> handleUiEvent(event)
            else -> Unit
        }
    }

    private fun NextBuilder.handleUiEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.CreateLunchClicked -> news(MainNews.OpenCreateLunchScreen)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: MainCommandResultEvent) {
        when (event) {
            is MainCommandResultEvent.GetLunchEventsSuccess -> {
                state { copy(events = LceState.Content(event.events)) }
            }
            is MainCommandResultEvent.GetLunchEventsError -> {
                state { copy(events = LceState.Error(cause = event.error)) }
                news(ShowError(error = event.error))
            }
        }
    }
}
