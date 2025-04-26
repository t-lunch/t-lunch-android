package ru.tinkoff.lunch.root.di

import ru.tinkoff.lunch.root.presentation.EntrypointStore
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class EntrypointComponent: BaseComponent {

    internal abstract fun getEntrypointStore(): EntrypointStore
}
