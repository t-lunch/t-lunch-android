package ru.tinkoff.lunch.root.sign_in.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class SignInUpdate :
    DslUpdate<SignInState, SignInEvent, SignInCommand, SignInNews>() {

    override fun NextBuilder.update(event: SignInEvent) {
        when (event) {
            is SignInCommandResultEvent -> handleCommandResultEvent(event)
            is SignInUiEvent -> handleUiEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: SignInUiEvent) {
        when (event) {
            is SignInUiEvent.SignUpClicked -> news(SignInNews.OpenSignUp)
            is SignInUiEvent.LoginClicked -> news(SignInNews.OpenMainScreen)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: SignInCommandResultEvent) {
        when (event) {
            else -> Unit
        }
    }
}
