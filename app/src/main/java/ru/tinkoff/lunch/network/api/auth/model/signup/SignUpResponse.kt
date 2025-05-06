package ru.tinkoff.lunch.network.api.auth.model.signup

import androidx.annotation.Keep

@Keep
data class SignUpResponse(
    val userId: String,
    val name: String,
    val surname: String,
    val tg: String,
    val office: String,
    val emoji: String,
)
