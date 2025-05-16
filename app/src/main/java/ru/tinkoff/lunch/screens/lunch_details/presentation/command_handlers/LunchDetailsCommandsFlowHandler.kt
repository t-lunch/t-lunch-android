package ru.tinkoff.lunch.screens.lunch_details.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsCommand
import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsEvent

internal typealias LunchDetailsCommandsFlowHandler = CommandsFlowHandler<LunchDetailsCommand, LunchDetailsEvent>
