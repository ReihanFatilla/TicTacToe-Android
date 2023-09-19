package com.zealsasia.reift.tictactoe.presentation

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

class TicTacToeViewModel(val ticTacToeUseCase: TicTacToeUseCase): ViewModel() {

    private val _state: MutableState<Resource<List<TicTacToe>>> = mutableStateOf(Resource.Loading())
    val state: State<Resource<List<TicTacToe>>> = _state

    fun getTicTacToeList(ticTacToeType: TicTacToeType){
        ticTacToeUseCase.getTicTacToeList(ticTacToeType = ticTacToeType).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = Resource.Success(result.data.orEmpty())
                }

                is Resource.Loading -> {
                    _state.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _state.value = Resource.Error(result.message ?: "Error On Fetching Game")
                }
            }
        }.launchIn(viewModelScope)

    }
}