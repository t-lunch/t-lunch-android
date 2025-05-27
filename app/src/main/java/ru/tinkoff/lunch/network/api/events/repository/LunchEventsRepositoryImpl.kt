package ru.tinkoff.lunch.network.api.events.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.network.api.events.pagination.LunchEventsSource

interface LunchEventsRepository {
    fun getLunchEvents(): Flow<PagingData<LunchEvent>>
}

class LunchEventsRepositoryImpl(
    private val lunchEventsSource: LunchEventsSource,
) : LunchEventsRepository {

    override fun getLunchEvents(): Flow<PagingData<LunchEvent>> {
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
