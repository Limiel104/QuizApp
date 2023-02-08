package com.example.quizapp.presentation.select_difficulty

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectDifficultyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(ScreenState())
    val state: State<ScreenState> = _state

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _state.value = _state.value.copy(
                        selectedCategory = category
                    )
        }

        savedStateHandle.get<String>("label")?.let { label ->
            _state.value = _state.value.copy(
                label = label
            )
        }

        savedStateHandle.get<Int>("iconId")?.let { iconId ->
            _state.value = _state.value.copy(
                iconId = iconId
            )
        }

        Log.i("TAG",_state.toString())
    }
}