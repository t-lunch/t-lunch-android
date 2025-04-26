package ru.tinkoff.lunch.root.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointEvent

internal typealias EntrypointCommandsFlowHandler = CommandsFlowHandler<EntrypointCommand, EntrypointEvent>
