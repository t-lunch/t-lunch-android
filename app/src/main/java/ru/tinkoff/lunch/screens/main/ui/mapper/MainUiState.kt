package ru.tinkoff.lunch.screens.main.ui.mapper

import ru.tinkoff.mobile.tech.ti_recycler.base.ViewTyped

data class MainUiState(
    val items: List<ViewTyped>,
    val areShimmersVisible: Boolean = false,
)
