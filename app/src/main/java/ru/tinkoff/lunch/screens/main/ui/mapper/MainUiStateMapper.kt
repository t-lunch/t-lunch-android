package ru.tinkoff.lunch.screens.main.ui.mapper

import ru.tinkoff.kotea.android.ui.ResourcesProvider
import ru.tinkoff.kotea.android.ui.UiStateMapper
import ru.tinkoff.lunch.common.lce.LceState
import ru.tinkoff.lunch.common.recycler.items.ItemHeader
import ru.tinkoff.lunch.screens.main.presentation.MainState

class MainUiStateMapper : UiStateMapper<MainState, MainUiState> {

    override fun ResourcesProvider.map(state: MainState): MainUiState {
        val items = buildList {
            when (state.events) {
                is LceState.Loading -> Unit
                is LceState.Content -> addAll(
                    listOf(
                        ItemHeader(text = "Обеды"),
                    )
                )
                is LceState.Error -> Unit
            }
        }
        return MainUiState(
            items = items,
            areShimmersVisible = state.events is LceState.Loading
        )
    }
}
