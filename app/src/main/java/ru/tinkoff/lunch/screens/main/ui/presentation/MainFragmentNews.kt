package ru.tinkoff.lunch.screens.main.ui.presentation

sealed interface MainFragmentNews {
    data class OpenLunchDetailsScreen(val id: String) : MainFragmentNews
}
