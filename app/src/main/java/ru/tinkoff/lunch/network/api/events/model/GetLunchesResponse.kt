package ru.tinkoff.lunch.network.api.events.model

import androidx.annotation.Keep

@Keep
data class GetLunchesResponse(
    val lunches: List<LunchEvent>,
)
