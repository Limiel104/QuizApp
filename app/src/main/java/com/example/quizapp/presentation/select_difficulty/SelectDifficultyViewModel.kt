package com.example.quizapp.presentation.select_difficulty

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.quizapp.R
import com.example.quizapp.util.Constants.arts_category
import com.example.quizapp.util.Constants.arts_label
import com.example.quizapp.util.Constants.film_category
import com.example.quizapp.util.Constants.film_label
import com.example.quizapp.util.Constants.food_category
import com.example.quizapp.util.Constants.food_label
import com.example.quizapp.util.Constants.geography_category
import com.example.quizapp.util.Constants.geography_label
import com.example.quizapp.util.Constants.history_category
import com.example.quizapp.util.Constants.history_label
import com.example.quizapp.util.Constants.knowledge_category
import com.example.quizapp.util.Constants.knowledge_label
import com.example.quizapp.util.Constants.music_category
import com.example.quizapp.util.Constants.music_label
import com.example.quizapp.util.Constants.science_category
import com.example.quizapp.util.Constants.science_label
import com.example.quizapp.util.Constants.society_category
import com.example.quizapp.util.Constants.society_label
import com.example.quizapp.util.Constants.sport_label
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectDifficultyViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(SelectionState())
    val state: State<SelectionState> = _state

    init {
        savedStateHandle.get<String>("category")?.let { category ->
            _state.value = state.value.copy(
                category = category
            )
        }

        setLabel()
        setIcon()

        Log.i("TAG",_state.toString())
    }

    private fun setLabel() {
        val labelId = when(_state.value.category) {
            arts_category -> arts_label
            food_category -> food_label
            geography_category -> geography_label
            music_category -> music_label
            society_category -> society_label
            film_category -> film_label
            knowledge_category -> knowledge_label
            history_category -> history_label
            science_category -> science_label
            else -> sport_label
        }
        _state.value = state.value.copy(
            label = labelId
        )
    }

    private fun setIcon() {
        val iconId = when(_state.value.category) {
            arts_category -> R.drawable.arts_big
            food_category -> R.drawable.food_big
            geography_category -> R.drawable.geography_big
            music_category -> R.drawable.music_big
            society_category -> R.drawable.society_big
            film_category -> R.drawable.film_big
            knowledge_category -> R.drawable.knowledge_big
            history_category -> R.drawable.history_big
            science_category -> R.drawable.science_big
            else -> R.drawable.sport_big
        }
        _state.value = state.value.copy(
            iconId = iconId
        )
    }
}