package ru.tinkoff.lunch.screens.main.ui.presentation

import ru.tinkoff.lunch.network.api.events.model.LunchEvent

data class MainFragmentState(
    val isLoading: Boolean = false,
    val lunches: List<LunchEvent> = emptyList(),
)
