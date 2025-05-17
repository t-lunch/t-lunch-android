package ru.tinkoff.lunch.network.api.events.model

import androidx.annotation.Keep
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse

@Keep
data class LunchEvent(
    val id: String = "123",
    val creator: String = "prettyCoolBoy",
    val place: String = "Кухня",
    val numberOfParticipants: Int = 2,
    val time: String = "13:00",
    val description: String = "Lunch time!",
    val users: List<SignUpResponse> = emptyList(),
    val name: String = "Петр",
    val surname: String = "Петров",
)
