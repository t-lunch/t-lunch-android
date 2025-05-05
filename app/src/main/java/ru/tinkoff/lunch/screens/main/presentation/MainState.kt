package ru.tinkoff.lunch.screens.main.presentation

import ru.tinkoff.lunch.common.lce.LceState
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

data class MainState(
    val events: LceState<List<LunchEvent>> = LceState.Loading,
)
