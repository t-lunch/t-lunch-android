package ru.tinkoff.lunch.screens.profile.presentation.command_handlers

import ru.tinkoff.kotea.core.CommandsFlowHandler
import ru.tinkoff.lunch.screens.profile.presentation.ProfileCommand
import ru.tinkoff.lunch.screens.profile.presentation.ProfileEvent

internal typealias ProfileCommandsFlowHandler = CommandsFlowHandler<ProfileCommand, ProfileEvent>
