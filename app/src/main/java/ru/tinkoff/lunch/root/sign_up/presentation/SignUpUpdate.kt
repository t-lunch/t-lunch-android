package ru.tinkoff.lunch.root.sign_up.presentation

import ru.tinkoff.kotea.core.dsl.DslUpdate
import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpBody

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
            is SignUpUiEvent.SignUpClicked -> {
                news(SignUpNews.ShowLoading)
                SignUpCommand.SignUp(
                    signUpBody = SignUpBody(
                        email = event.login,
                        password = event.password,
                        name = event.name,
                        surname = event.surname,
                        tg = event.telegram,
                        emoji = state.selectedEmoji,
                        office = state.selectedOffice,
                    )
                ).let { commands(it) }
            }
        }
    }

    private fun NextBuilder.handleCommandResultEvent(event: SignUpCommandResultEvent) {
        when (event) {
            is SignUpCommandResultEvent.SignUpSuccess -> news(SignUpNews.OpenMainScreen)
            is SignUpCommandResultEvent.SignUpError -> news(SignUpNews.ShowError(event.message))
        }
    }
}
