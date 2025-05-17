package ru.tinkoff.lunch.network.api.events.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.tinkoff.lunch.network.api.events.LunchEventsApi
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

//class LunchEventsSource(
//    private val api: LunchEventsApi,
////    private val
//) : PagingSource<Int, LunchEvent>() {
//
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LunchEvent> {
//        return try {
//            val position = params.key ?: 0
//            val response = api.getLunches()
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, LunchEvent>): Int? {
//        TODO("Not yet implemented")
//    }
//}
