package ru.tinkoff.lunch.screens.profile.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.screens.profile.presentation.ProfileState
import ru.tinkoff.lunch.screens.profile.presentation.ProfileStore
import ru.tinkoff.lunch.screens.profile.presentation.ProfileUpdate

internal interface ProfileModule {

    fun getProfileStore(): ProfileStore
}

internal fun ProfileModule() = object : ProfileModule {
    override fun getProfileStore(): ProfileStore {
        return KoteaStore(
            initialState = ProfileState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(),
            update = ProfileUpdate(),
        )
    }
}
