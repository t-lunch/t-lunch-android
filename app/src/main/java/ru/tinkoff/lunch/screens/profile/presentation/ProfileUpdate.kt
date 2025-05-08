package ru.tinkoff.lunch.screens.profile.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class ProfileUpdate : DslUpdate<ProfileState, ProfileEvent, ProfileCommand, ProfileNews>() {

    override fun NextBuilder.update(event: ProfileEvent) {
        when (event) {
            is ProfileCommandResultEvent -> handleCommandResultEvent(event)
            is ProfileUiEvent -> handleProfileUiEvent(event)
            else -> Unit
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: ProfileCommandResultEvent) {
        when (event) {
            else -> Unit
        }
    }

    private fun NextBuilder.handleProfileUiEvent(event: ProfileUiEvent) {
        when (event) {
            is ProfileUiEvent.LogoutClicked -> {
                news(ProfileNews.Logout)
            }
        }
    }
}
