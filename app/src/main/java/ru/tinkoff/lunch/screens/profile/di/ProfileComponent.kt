package ru.tinkoff.lunch.screens.profile.di

import ru.tinkoff.lunch.screens.profile.presentation.ProfileStore
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class ProfileComponent: BaseComponent {

    internal abstract fun getProfileStore(): ProfileStore
}
