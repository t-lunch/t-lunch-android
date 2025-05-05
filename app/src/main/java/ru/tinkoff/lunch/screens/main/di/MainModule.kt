package ru.tinkoff.lunch.screens.main.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.screens.main.presentation.MainCommand.GetLunchEvents
import ru.tinkoff.lunch.screens.main.presentation.MainState
import ru.tinkoff.lunch.screens.main.presentation.MainStore
import ru.tinkoff.lunch.screens.main.presentation.MainUpdate
import ru.tinkoff.lunch.screens.main.presentation.command_handlers.GetLunchEventsCommandHandler
import ru.tinkoff.lunch.screens.main.ui.mapper.MainUiStateMapper

internal interface MainModule {

    fun getMainStore(): MainStore
    val uiStateMapper: MainUiStateMapper
}

internal fun MainModule() = object : MainModule {

    override fun getMainStore(): MainStore {
        return KoteaStore(
            initialState = MainState(),
            initialCommands = listOf(GetLunchEvents),
            commandsFlowHandlers = listOf(GetLunchEventsCommandHandler()),
            update = MainUpdate(),
        )
    }

    override val uiStateMapper: MainUiStateMapper
        get() = MainUiStateMapper()
}
