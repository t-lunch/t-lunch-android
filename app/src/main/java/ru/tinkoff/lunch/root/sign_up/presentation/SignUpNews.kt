package ru.tinkoff.lunch.root.sign_up.presentation

internal sealed interface SignUpNews {
    object OpenSignIn : SignUpNews
    object OpenMainScreen : SignUpNews
    data class ShowError(val message: String?) : SignUpNews
    object ShowLoading : SignUpNews
}
