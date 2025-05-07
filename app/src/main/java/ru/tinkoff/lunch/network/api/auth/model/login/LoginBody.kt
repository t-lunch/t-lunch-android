package ru.tinkoff.lunch.network.api.auth.model.login

import androidx.annotation.Keep

@Keep
data class LoginBody(
    val email: String,
    val password: String,
)
