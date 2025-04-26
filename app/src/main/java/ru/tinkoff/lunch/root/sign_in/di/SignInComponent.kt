package ru.tinkoff.lunch.root.sign_in.di

import ru.tinkoff.lunch.root.sign_in.presentation.SignInStore
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class SignInComponent: BaseComponent {

    internal abstract fun getSignInStore(): SignInStore
}
