package ru.tinkoff.lunch.root.sign_in.di

import ru.tinkoff.kotea.core.KoteaStore
import ru.tinkoff.lunch.root.sign_in.presentation.SignInState
import ru.tinkoff.lunch.root.sign_in.presentation.SignInStore
import ru.tinkoff.lunch.root.sign_in.presentation.SignInUpdate

internal interface SignInModule {

    fun getSignInStore(): SignInStore
}

internal fun SignInModule() = object : SignInModule {
    override fun getSignInStore(): SignInStore {
        return KoteaStore(
            initialState = SignInState(),
            initialCommands = listOf(),
            commandsFlowHandlers = listOf(),
            update = SignInUpdate(),
        )
    }
}
