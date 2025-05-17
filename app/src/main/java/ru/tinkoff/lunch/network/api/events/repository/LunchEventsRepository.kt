package ru.tinkoff.lunch.network.api.events.repository

import ru.tinkoff.lunch.network.api.events.LunchEventsApi

class LunchEventsRepository(
    private val lunchEventsApi: LunchEventsApi,
) {
    suspend fun getLunchEvents() {

    }
}
