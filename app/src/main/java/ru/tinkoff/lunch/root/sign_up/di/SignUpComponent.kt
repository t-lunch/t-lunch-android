package ru.tinkoff.lunch.root.sign_up.di

import ru.tinkoff.lunch.root.sign_up.presentation.SignUpStore
import ru.tinkoff.lunch.root.sign_up.ui.mapper.SignUpUiStateMapper
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class SignUpComponent : BaseComponent {

    internal abstract fun getSignUpStore(): SignUpStore
    internal abstract val uiStateMapper: SignUpUiStateMapper
}
