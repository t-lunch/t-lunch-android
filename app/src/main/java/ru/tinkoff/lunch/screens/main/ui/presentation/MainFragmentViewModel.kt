package ru.tinkoff.lunch.screens.main.ui.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.tinkoff.lunch.network.api.events.model.LunchEvent
import ru.tinkoff.lunch.network.api.events.repository.LunchEventsRepository
import ru.tinkoff.lunch.screens.main.ui.presentation.MainFragmentNews
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val repository: LunchEventsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainFragmentState())
    val uiState: StateFlow<MainFragmentState> = _uiState.asStateFlow()

    private val _news = MutableSharedFlow<MainFragmentNews>()
    val news: SharedFlow<MainFragmentNews> = _news.asSharedFlow()

    init {
        loadLunches()
    }

    fun onEvent(event: MainUiEvent) {
        when (event) {
            MainUiEvent.Refresh -> loadLunches()
            MainUiEvent.CreateLunchClicked -> viewModelScope.launch {
                _news.emit(MainFragmentNews.OpenCreateLunchScreen)
            }
            is MainUiEvent.LunchDetailsClicked -> viewModelScope.launch {
                _news.emit(MainFragmentNews.OpenLunchDetailsScreen(event.id))
            }
        }
    }

    private fun loadLunches() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // todo: val list = repository.getLunchEvents()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        lunches = listOf(LunchEvent(), LunchEvent(), LunchEvent()),
                    )
                }
            } catch (e: Throwable) {
                _uiState.update { it.copy(isLoading = false) }
                _news.emit(MainFragmentNews.ShowError(e))
            }
        }
    }
}
