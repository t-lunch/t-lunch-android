package ru.tinkoff.lunch.network.api.events

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query
import ru.tinkoff.lunch.network.api.events.model.GetLunchesResponse

interface LunchEventsApi {

    @GET("v1/lunches")
    suspend fun getLunches(
        @Query("user_id") userId: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 10,
    ): ApiResponse<GetLunchesResponse>
}
