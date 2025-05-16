package ru.tinkoff.lunch.screens.lunch_details.presentation

internal sealed interface LunchDetailsNews {
    data class OpenTelegram(val telegramId: String) : LunchDetailsNews
}
