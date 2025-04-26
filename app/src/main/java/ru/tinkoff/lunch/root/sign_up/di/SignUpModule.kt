package ru.tinkoff.lunch.root.sign_up.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpState
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpStore
import ru.tinkoff.lunch.root.sign_up.presentation.SignUpUpdate

internal interface SignUpModule {

    fun getSignUpStore(): SignUpStore
}

internal fun SignUpModule() = object : SignUpModule {
    override fun getSignUpStore(): SignUpStore {
        return KoteaStore(
            initialState = SignUpState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(),
            update = SignUpUpdate(),
        )
    }
}
