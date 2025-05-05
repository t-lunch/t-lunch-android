package ru.tinkoff.lunch.network.api.events.model

import androidx.annotation.Keep

@Keep
data class LunchEvent(
    val id: String = "123",
    val creator: String = "prettyCoolBoy",
    val location: String = "Кухня",
    val numberOfParticipants: Int = 2,
)
