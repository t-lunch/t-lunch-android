package ru.tinkoff.lunch.root.presentation.command_handlers

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flatMapLatest
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.presentation.EntrypointCommand
import ru.tinkoff.lunch.root.presentation.EntrypointEvent

class DeleteTokensCommandHandler(
    private val tokenManager: JwtTokenManager,
) : EntrypointCommandsFlowHandler {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun handle(commands: Flow<EntrypointCommand>): Flow<EntrypointEvent> {
        return commands.filterIsInstance<EntrypointCommand.DeleteTokens>().flatMapLatest {
            tokenManager.clearAllTokens()
            emptyFlow()
        }
    }
}
