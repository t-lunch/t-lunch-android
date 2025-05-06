package ru.tinkoff.lunch.network.api.auth.model.refresh

import androidx.annotation.Keep

@Keep
data class RefreshBody(
    val refreshToken: String?,
)
