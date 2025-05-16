package ru.tinkoff.lunch.screens.lunch_details.di

import ru.tinkoff.lunch.screens.lunch_details.presentation.LunchDetailsStore
import ru.tinkoff.lunch.utils.di.BaseComponent

abstract class LunchDetailsComponent: BaseComponent {

    internal abstract fun getLunchDetailsStore(lunchId: String): LunchDetailsStore
}
