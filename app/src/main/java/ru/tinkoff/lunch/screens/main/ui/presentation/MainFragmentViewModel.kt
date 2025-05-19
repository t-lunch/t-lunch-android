package ru.tinkoff.lunch.screens.main.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.tinkoff.lunch.network.api.events.repository.LunchEventsRepository
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: LunchEventsRepository,
) : ViewModel() {

    private var searchJob: Job? = null

    private val _uiState = MutableStateFlow(MainFragmentState())
    val uiState: StateFlow<MainFragmentState> = _uiState.onStart {
        observeLunches()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), _uiState.value)

    private val _news = MutableSharedFlow<MainFragmentNews>()
    val news: SharedFlow<MainFragmentNews> = _news.asSharedFlow()

    fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.LunchDetailsClicked -> viewModelScope.launch {
                _news.emit(MainFragmentNews.OpenLunchDetailsScreen(event.id))
            }
        }
    }

    private fun observeLunches() {
        viewModelScope.launch {
            searchJob = null
            searchJob = getLunchesFromPager()
        }
    }

    private fun getLunchesFromPager() = viewModelScope.launch {
        _uiState.update {
            it.copy(
                lunchesFlow = repository.getLunchEvents()
            )
        }
    }
}
