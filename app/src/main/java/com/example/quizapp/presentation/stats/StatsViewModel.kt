package com.example.quizapp.presentation.stats

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizapp.domain.use_case.QuizUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(
    private val quizUseCases: QuizUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _statsListState = mutableStateOf(StatsListState())
    val statsListState: State<StatsListState> = _statsListState

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _statsListState.value = statsListState.value.copy(
                category = category
            )
        }

        getStats(_statsListState.value.category)
    }

    private fun getStats(category: String) {
        viewModelScope.launch {
            quizUseCases.getStatsUseCase(category).collect { results ->
                _statsListState.value = statsListState.value.copy(
                    statsList = results
                )
            }
        }
    }
}