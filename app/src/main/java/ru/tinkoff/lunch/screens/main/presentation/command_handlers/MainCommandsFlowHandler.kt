package ru.tinkoff.lunch.screens.main.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.screens.main.presentation.MainCommand
import ru.tinkoff.lunch.screens.main.presentation.MainEvent

internal typealias MainCommandsFlowHandler = CommandsFlowHandler<MainCommand, MainEvent>
