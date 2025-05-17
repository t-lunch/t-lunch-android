package ru.tinkoff.lunch.network.api.events

import retrofit2.http.GET
import retrofit2.http.Query
import ru.tinkoff.lunch.network.api.events.model.LunchEvent

interface LunchEventsApi {

    @GET("v1/lunches")
    suspend fun getLunches(
        @Query("user_id") userId: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10,
    ): List<LunchEvent>
}
