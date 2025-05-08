package ru.tinkoff.lunch.root.sign_up.presentation

import ru.tinkoff.lunch.network.api.auth.model.signup.SignUpBody

sealed interface SignUpCommand {
    data class SignUp(val signUpBody: SignUpBody) : SignUpCommand
}
