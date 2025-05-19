package ru.tinkoff.lunch.network.api.events.model

import androidx.annotation.Keep
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse
import java.util.Date

@Keep
data class LunchEvent(
    val id: String = "123",
    val name: String = "Петр",
    val surname: String = "Петров",
    val place: String = "Кухня",
    val timeCustom: String? = "13:00",
    val time: Date = Date(),
    val numberOfParticipants: Int = 2,
    val description: String = "Lunch time!",
    val users: List<SignUpResponse> = emptyList(),
)
