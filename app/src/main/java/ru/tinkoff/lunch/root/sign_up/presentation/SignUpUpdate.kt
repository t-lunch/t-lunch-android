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
            is SignUpUiEvent.EmojiClicked -> state { copy(selectedEmoji = event.emoji) }
            is SignUpUiEvent.OfficeClicked -> state { copy(selectedOffice = event.office) }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: SignUpCommandResultEvent) {
        when (event) {
            else -> Unit
        }
    }
}
