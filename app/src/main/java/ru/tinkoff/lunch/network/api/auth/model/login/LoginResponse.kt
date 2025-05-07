package ru.tinkoff.lunch.network.api.auth.model.login

import androidx.annotation.Keep

@Keep
data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
)
