package ru.tinkoff.lunch.root.sign_up.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.network.api.auth.repository.AuthRepository
import ru.tinkoff.lunch.network.common.token.JwtTokenManager
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpState
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpStore
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpUpdate
import ru.tinkoff.lunch.root.sign_up.presentation.command_handlers.SignUpCommandHandler
import ru.tinkoff.lunch.root.sign_up.ui.mapper.SignUpUiStateMapper

internal interface SignUpModule {

    fun getSignUpStore(): SignUpStore
    val uiStateMapper: SignUpUiStateMapper
}

internal fun SignUpModule(
    authRepository: AuthRepository,
    tokenManager: JwtTokenManager,
) = object : SignUpModule {

    override fun getSignUpStore(): SignUpStore {
        return KoteaStore(
            initialState = SignUpState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(
                SignUpCommandHandler(
                    authRepository = authRepository,
                    tokenManager = tokenManager,
                ),
            ),
            update = SignUpUpdate(),
        )
    }

    override val uiStateMapper: SignUpUiStateMapper
        get() = SignUpUiStateMapper()
}
