package ru.tinkoff.lunch.root.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.presentation.EntrypointState
import ru.tinkoff.lunch.root.presentation.EntrypointStore
import ru.tinkoff.lunch.root.presentation.EntrypointUpdate
import ru.tinkoff.lunch.root.presentation.command_handlers.AuthenticationCommandHandler
import ru.tinkoff.lunch.root.presentation.command_handlers.DeleteTokensCommandHandler

internal interface EntrypointModule {

    fun getEntrypointStore(): EntrypointStore
}

internal fun EntrypointModule(
    authRepository: AuthRepository,
    tokenManager: JwtTokenManager,
) = object : EntrypointModule {
    override fun getEntrypointStore(): EntrypointStore {
        return KoteaStore(
            initialState = EntrypointState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(
                AuthenticationCommandHandler(
                    authRepository = authRepository,
                    tokenManager = tokenManager,
                ),
                DeleteTokensCommandHandler(
                    tokenManager = tokenManager,
                )
            ),
            update = EntrypointUpdate(),
        )
    }
}
