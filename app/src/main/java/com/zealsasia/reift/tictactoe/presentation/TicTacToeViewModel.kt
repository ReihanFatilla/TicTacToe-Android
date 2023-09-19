package com.zealsasia.reift.tictactoe.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class TicTacToeViewModel(val ticTacToeUseCase: TicTacToeUseCase) : ViewModel() {

    private val _onGoingState: MutableState<Resource<List<TicTacToe>>> = mutableStateOf(Resource.Loading())
    val onGoingState: State<Resource<List<TicTacToe>>> = _onGoingState

    private val _finishedState: MutableState<Resource<List<TicTacToe>>> = mutableStateOf(Resource.Loading())
    val finishedState: State<Resource<List<TicTacToe>>> = _finishedState

    fun getFinishedList() {
        ticTacToeUseCase.getTicTacToeList(TicTacToeType.FINISHED).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _finishedState.value = Resource.Success(result.data.orEmpty())
                }

                is Resource.Loading -> {
                    _finishedState.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _finishedState.value = Resource.Error(result.message ?: "Error On Fetching Game")
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getOnGoingList() {
        ticTacToeUseCase.getTicTacToeList(TicTacToeType.ONGOING).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _onGoingState.value = Resource.Success(result.data.orEmpty())
                }

                is Resource.Loading -> {
                    _onGoingState.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _onGoingState.value = Resource.Error(result.message ?: "Error On Fetching Game")
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getFinishedList()
        getOnGoingList()
    }
}