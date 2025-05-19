package ru.tinkoff.lunch.screens.main.ui.presentation

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

data class MainFragmentState(
    val lunchesFlow: Flow<PagingData<LunchEvent>> = emptyFlow(),
)
