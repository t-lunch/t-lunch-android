package ru.tinkoff.lunch.root.sign_in.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.sign_in.presentation.SignInState
import ru.tinkoff.lunch.root.sign_in.presentation.SignInStore
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUpdate
import ru.tinkoff.lunch.root.sign_in.presentation.command_handlers.SignInCommandHandler

internal interface SignInModule {

    fun getSignInStore(): SignInStore
}

internal fun SignInModule(
    authRepository: AuthRepository,
    tokenManager: JwtTokenManager,
) = object : SignInModule {
    override fun getSignInStore(): SignInStore {
        return KoteaStore(
            initialState = SignInState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(
                SignInCommandHandler(
                    authRepository = authRepository,
                    tokenManager = tokenManager,
                ),
            ),
            update = SignInUpdate(),
        )
    }
}
