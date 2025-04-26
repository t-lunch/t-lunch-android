package ru.tinkoff.lunch.root.sign_up.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpCommand
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpEvent

internal typealias SignUpCommandsFlowHandler = CommandsFlowHandler<SignUpCommand, SignUpEvent>
