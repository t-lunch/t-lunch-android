package ru.tinkoff.lunch.network.api.events.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skydoves.sandwich.getOrNull
import com.skydoves.sandwich.isSuccess
import ru.tinkoff.lunch.network.api.events.LunchEventsApi
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.network.common.user_info.UserInfoManager

private const val PAGE_SIZE = 10

data class LunchEventsSource(
    private val api: LunchEventsApi,
    private val userInfoManager: UserInfoManager,
) : PagingSource<Int, LunchEvent>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LunchEvent> {
        return try {
            val offset = params.key ?: 0

            val userId =
                userInfoManager.getUserId() ?: return LoadResult.Error(Exception("User id is null"))
            val response = api.getLunches(userId = userId, offset = offset)

            if (response.isSuccess && response.getOrNull() != null) {
                val nextKey = if (response.getOrNull()!!.lunches.isEmpty()) {
                    null
                } else {
                    offset + PAGE_SIZE
                }
                LoadResult.Page(
                    data = response.getOrNull()!!.lunches,
                    prevKey = null,
                    nextKey = nextKey,
                )
            } else {
                LoadResult.Error(throw Exception("No Response from try-catch block by lunches api"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, LunchEvent>): Int? {
        return null
    }
}
