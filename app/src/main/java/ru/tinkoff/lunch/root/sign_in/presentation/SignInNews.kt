package ru.tinkoff.lunch.root.sign_in.presentation

internal sealed interface SignInNews {
    object OpenSignUp : SignInNews
    object OpenMainScreen : SignInNews
    object ShowLoading : SignInNews
    data class ShowError(val message: String?) : SignInNews
}
