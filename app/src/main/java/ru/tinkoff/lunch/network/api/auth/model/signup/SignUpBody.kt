package ru.tinkoff.lunch.network.api.auth.model.signup

import androidx.annotation.Keep

@Keep
data class SignUpBody(
    val name: String,
    val surname: String,
    val tg: String,
    val office: String,
    val emoji: String,
    val email: String,
    val password: String,
)
