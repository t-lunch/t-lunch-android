package ru.tinkoff.lunch.screens.lunch_details.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsCommand.GetLunchDetails
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsState
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsStore
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsUpdate
import ru.tinkoff.lunch.screens.lunch_details.presentation.command_handlers.GetLunchDetailsCommandHandler

internal interface LunchDetailsModule {

    fun getLunchDetailsStore(lunchId: String): LunchDetailsStore
}

internal fun LunchDetailsModule() = object : LunchDetailsModule {

    override fun getLunchDetailsStore(lunchId: String): LunchDetailsStore {
        return KoteaStore(
            initialState = LunchDetailsState(lunch = LunchEvent()),
            initialCommands = listOf(GetLunchDetails(id = lunchId)),
            commandsFlowHandlers = listOf(
                GetLunchDetailsCommandHandler(),
            ),
            update = LunchDetailsUpdate(),
        )
    }
}
