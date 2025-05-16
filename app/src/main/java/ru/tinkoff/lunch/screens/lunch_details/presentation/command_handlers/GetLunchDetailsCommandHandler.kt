package ru.tinkoff.lunch.screens.lunch_details.presentation.command_handlers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.transform
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpResponse
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsCommand
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsCommandResultEvent
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsEvent

class GetLunchDetailsCommandHandler : LunchDetailsCommandsFlowHandler {

    override fun handle(commands: Flow<LunchDetailsCommand>): Flow<LunchDetailsEvent> {
        return commands.filterIsInstance<LunchDetailsCommand.GetLunchDetails>().transform {
            val lunch = LunchEvent(
                id = "123",
                creator = "nightshift48",
                place = "Кухня",
                numberOfParticipants = 2,
                time = "13:00",
                description = "Какое-то примечание (не редактируемое) Какое-то примечание (не редактируемое) " +
                        "Какое-то примечание (не редактируемое) Какое-то примечание (не редактируемое) " +
                        "Какое-то примечание (не редактируемое)",
                users = listOf(
                    SignUpResponse(
                        userId = "1",
                        name = "Павел",
                        surname = "Медведев",
                        tg = "nightshift48",
                        office = "Кухня",
                        emoji = "🍽",
                    ),
                    SignUpResponse(
                        userId = "2",
                        name = "Шерон",
                        surname = "Шеронов",
                        tg = "Sheron4ik",
                        office = "Кухня",
                        emoji = "🍽",
                    ),
                ),
            )
            emit(LunchDetailsCommandResultEvent.GetLunchDetailsSuccess(lunch = lunch))
        }
    }
}
