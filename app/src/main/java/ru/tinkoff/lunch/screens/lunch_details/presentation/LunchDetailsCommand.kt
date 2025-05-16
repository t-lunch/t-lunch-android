package ru.tinkoff.lunch.screens.lunch_details.presentation

sealed interface LunchDetailsCommand {
    data class GetLunchDetails(val id: String) : LunchDetailsCommand
}
