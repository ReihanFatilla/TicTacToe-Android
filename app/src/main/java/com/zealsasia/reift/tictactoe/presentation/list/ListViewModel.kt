package com.zealsasia.reift.tictactoe.presentation.list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.Resource
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ListViewModel(val ticTacToeUseCase: TicTacToeUseCase) : ViewModel() {

    private val _listState: MutableState<Resource<List<TicTacToe>>> = mutableStateOf(Resource.Loading())
    val listState: State<Resource<List<TicTacToe>>> = _listState

    fun getTicTacToeList() {
        ticTacToeUseCase.getTicTacToeList().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _listState.value = Resource.Success(result.data.orEmpty())
                }

                is Resource.Loading -> {
                    _listState.value = Resource.Loading()
                }

                is Resource.Error -> {
                    _listState.value = Resource.Error(result.message ?: "Error On Fetching Game")
                }
            }
        }.launchIn(viewModelScope)
    }

    init {
        getTicTacToeList()
    }
}