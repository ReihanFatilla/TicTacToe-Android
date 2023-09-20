package com.zealsasia.reift.tictactoe.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(
    val ticTacToeUseCase: TicTacToeUseCase,
) : ViewModel() {

    private val _ticTacToeState = MutableStateFlow(TicTacToe.initial)
    val ticTacToeState get() = _ticTacToeState as StateFlow<TicTacToe>

    var openDialog by mutableStateOf(false)

    var isFinished by mutableStateOf(false)
    var isUpdateMode by mutableStateOf(false)
    var postUpdateState by mutableStateOf<Resource<String?>?>(null)

    fun setOnClickState(row: Int, column: Int) {
        if (ticTacToeState.value.gameState[row][column] == "" && !isFinished) {
            _ticTacToeState.getAndUpdate {
                val newState = it.gameState.map { it.toMutableList() }
                newState[row][column] = it.currentTurn
                checkIfGameFinished(newState)
                it.copy(
                    gameState = newState,
                    ticTacToeType = if(isFinished) TicTacToeType.FINISHED else TicTacToeType.ONGOING,
                    currentTurn = if(!isFinished) if (it.currentTurn == "X") "O" else "X" else it.currentTurn
                )
            }
        }
    }

    fun resetTicTacToe(){
        _ticTacToeState.update { TicTacToe.initial }
        isFinished = false
        isUpdateMode = false
    }

    private fun checkIfGameFinished(state: List<MutableList<String>>) {
        if (Utils.isGameFinished(state)) {
            isFinished = true
        }
    }

    fun setCurrentTicTacToe(ticTacToe: TicTacToe) {
        isFinished = ticTacToe.ticTacToeType == TicTacToeType.FINISHED
        isUpdateMode = true
        _ticTacToeState.update {
            ticTacToe
        }
    }

    fun saveTicTacToe(name: String = "") {
        ticTacToeUseCase.saveTicTacToe(ticTacToeState.value.copy(name = name)).onEach { result ->
            postUpdateState = when (result) {
                is Resource.Success -> Resource.Success(result.data)
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(result.message ?: "Error")
            }
        }.launchIn(viewModelScope)
        resetTicTacToe()
    }

    fun updateTicTacToe(name: String = ticTacToeState.value.name.orEmpty()) {
        val id = ticTacToeState.value.id
        ticTacToeUseCase.saveTicTacToe(ticTacToeState.value.copy(id = id, name = name)).onEach { result ->
            postUpdateState = when (result) {
                is Resource.Success -> Resource.Success(result.data)
                is Resource.Loading -> Resource.Loading()
                is Resource.Error -> Resource.Error(result.message ?: "Error")
            }
        }.launchIn(viewModelScope)
        resetTicTacToe()
    }

    fun checkIfGameStateEmpty(): Boolean {
        return ticTacToeState.value.gameState == TicTacToe.initial.gameState
    }

}