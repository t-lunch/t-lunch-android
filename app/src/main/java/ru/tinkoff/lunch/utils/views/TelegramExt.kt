package ru.tinkoff.lunch.utils.views

import android.net.Uri
import androidx.core.net.toUri

fun String.toTelegramUri(): Uri {
    return "https://t.me/$this".toUri()
}
