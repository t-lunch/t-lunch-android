package ru.tinkoff.lunch.root.sign_up.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate

internal class SignUpUpdate :
    DslUpdate<SignUpState, SignUpEvent, SignUpCommand, SignUpNews>() {

    override fun NextBuilder.update(event: SignUpEvent) {
        when (event) {
            is SignUpCommandResultEvent -> handleCommandResultEvent(event)
            is SignUpUiEvent -> handleUiEvent(event)
        }
    }

    private fun NextBuilder.handleUiEvent(event: SignUpUiEvent) {
        when (event) {
            is SignUpUiEvent.SignInClicked -> news(SignUpNews.OpenSignIn)
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: SignUpCommandResultEvent) {
        when (event) {
            else -> Unit
        }
    }
}
