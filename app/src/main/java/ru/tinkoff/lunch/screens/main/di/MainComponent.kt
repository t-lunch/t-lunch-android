package ru.tinkoff.lunch.screens.main.di

import ru.tinkoff.lunch.screens.main.presentation.MainStore
import ru.tinkoff.lunch.screens.main.ui.mapper.MainUiStateMapper
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class MainComponent: BaseComponent {

    internal abstract fun getMainStore(): MainStore
    internal abstract val uiStateMapper: MainUiStateMapper
}
