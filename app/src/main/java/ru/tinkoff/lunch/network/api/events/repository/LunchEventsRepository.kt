package ru.tinkoff.lunch.network.api.events.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.network.api.events.pagination.LunchEventsSource

class LunchEventsRepository(
    private val lunchEventsSource: LunchEventsSource,
) {

    fun getLunchEvents(): Flow<PagingData<LunchEvent>> {
        val pager = Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = { lunchEventsSource.copy() }
        )
        return pager.flow
    }
}
