package ru.tinkoff.lunch.screens.main.presentation.command_handlers

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.main.presentation.MainCommand
import ru.tinkoff.lunch.screens.main.presentation.MainCommandResultEvent
import ru.tinkoff.lunch.screens.main.presentation.MainCommandResultEvent.GetLunchEventsSuccess
import ru.tinkoff.lunch.screens.main.presentation.MainEvent

internal class GetLunchEventsCommandHandler : MainCommandsFlowHandler {

    override fun handle(commands: Flow<MainCommand>): Flow<MainEvent> {
        return commands.filterIsInstance<MainCommand.GetLunchEvents>().transform {
            delay(2500)
            emit(GetLunchEventsSuccess(listOf(LunchEvent(), LunchEvent(), LunchEvent())))
//            emit(MainCommandResultEvent.GetLunchEventsError(Throwable()))
        }
    }
}
