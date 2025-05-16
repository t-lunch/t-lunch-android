package ru.tinkoff.lunch.screens.lunch_details.presentation

import ru.tinkoff.lunch.network.api.events.model.LunchEvent

data class LunchDetailsState(
    // todo: make nullable
    val lunch: LunchEvent,
)
