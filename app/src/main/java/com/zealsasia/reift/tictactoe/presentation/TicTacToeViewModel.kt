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

    private val _state: MutableState<Resource<List<TicTacToe>>> = mutableStateOf(Resource.Loading())
    val state: State<Resource<List<TicTacToe>>> = _state

    fun getTicTacToeList(ticTacToeType: TicTacToeType) {
        Log.i("getTicTacToeLists", "getTicTacToeLists: Runned")
        ticTacToeUseCase.getTicTacToeList(ticTacToeType = ticTacToeType).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    Log.i("getTicTacToeLists", "getTicTacToeLists: Success")
                    _state.value = Resource.Success(result.data.orEmpty())
                }

                is Resource.Loading -> {
                    Log.i("getTicTacToeLists", "getTicTacToeLists: Loading")
                    _state.value = Resource.Loading()
                }

                is Resource.Error -> {
                    Log.i("getTicTacToeLists", "getTicTacToeLists: ${result.message}")
                    _state.value = Resource.Error(result.message ?: "Error On Fetching Game")
                }
            }
        }.launchIn(viewModelScope)
    }
}