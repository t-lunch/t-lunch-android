package ru.tinkoff.lunch.root.sign_in.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.root.sign_in.presentation.SignInCommand
import ru.tinkoff.lunch.root.sign_in.presentation.SignInEvent

internal typealias SignInCommandsFlowHandler = CommandsFlowHandler<SignInCommand, SignInEvent>
