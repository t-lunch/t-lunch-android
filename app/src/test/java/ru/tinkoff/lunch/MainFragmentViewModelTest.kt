package ru.tinkoff.lunch

import androidx.paging.PagingData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.network.api.events.repository.LunchEventsRepository
import ru.tinkoff.lunch.screens.main.ui.presentation.*
import kotlin.test.assertEquals
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@OptIn(ExperimentalCoroutinesApi::class)
class MainFragmentViewModelTest {

    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var repository: LunchEventsRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        repository = mockk(relaxed = true)
        viewModel = MainFragmentViewModel(repository)
        Dispatchers.setMain(dispatcher = testDispatcher)
    }

    @Test
    fun `when viewModel is initialized, then observe lunches and update state`() = runTest(
        timeout = 10L.toDuration(DurationUnit.SECONDS),
    ) {
        val expectedFlow = emptyFlow<PagingData<LunchEvent>>()
        every { repository.getLunchEvents() } returns expectedFlow

        val state = viewModel.uiState.first()

        assertEquals(expectedFlow, state.lunchesFlow)
    }

    @Test
    fun `when LunchDetailsClicked event is received, then emit OpenLunchDetailsScreen news`() = runTest(
        timeout = 10L.toDuration(DurationUnit.SECONDS),
    ) {
        val event = MainUiEvent.LunchDetailsClicked(id = "123")
        viewModel.onEvent(event)
        val news = MainFragmentNews.OpenLunchDetailsScreen(
            id = "123"
        )
        assertEquals(MainFragmentNews.OpenLunchDetailsScreen(id = "123"), news)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }
}
