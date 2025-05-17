package ru.tinkoff.lunch.screens.main.ui.presentation

import ru.tinkoff.lunch.common.lce.LceState
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

data class MainFragmentState(
    val lunches: LceState<List<LunchEvent>> = LceState.Loading,
)
