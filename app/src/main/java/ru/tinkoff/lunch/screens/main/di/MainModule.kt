package ru.tinkoff.lunch.screens.main.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.screens.main.presentation.MainState
import ru.tinkoff.lunch.screens.main.presentation.MainStore
import ru.tinkoff.lunch.screens.main.presentation.MainUpdate

internal interface MainModule {

    fun getMainStore(): MainStore
}

internal fun MainModule() = object : MainModule {
    override fun getMainStore(): MainStore {
        return KoteaStore(
            initialState = MainState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(),
            update = MainUpdate(),
        )
    }
}
