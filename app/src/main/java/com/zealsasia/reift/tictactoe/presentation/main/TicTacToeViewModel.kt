package com.zealsasia.reift.tictactoe.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.Utils.isGameFinished
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
    var isUpdateMode by mutableStateOf(false)
    var isHistoryMode by mutableStateOf(false)
    var postUpdateState by mutableStateOf<Resource<String?>?>(null)

    fun setOnClickState(row: Int, column: Int) {
        if (ticTacToeState.value.gameState[row][column] == "" && ticTacToeState.value.ticTacToeType == TicTacToeType.ONGOING) {
            _ticTacToeState.getAndUpdate {
                val newState = it.gameState.map { it.toMutableList() }
                newState[row][column] = it.currentTurn
                it.copy(
                    gameState = newState,
                    ticTacToeType = if(newState.isGameFinished()) TicTacToeType.FINISHED else TicTacToeType.ONGOING,
                    currentTurn = if(!newState.isGameFinished()) if (it.currentTurn == "X") "O" else "X" else it.currentTurn
                )
            }
        }
    }

    fun resetTicTacToe(){
        _ticTacToeState.update { TicTacToe.initial }
        isUpdateMode = false
    }

    fun setCurrentTicTacToe(ticTacToe: TicTacToe) {
        isHistoryMode = ticTacToe.ticTacToeType == TicTacToeType.FINISHED
        isUpdateMode = ticTacToe.ticTacToeType == TicTacToeType.ONGOING
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
        ticTacToeUseCase.updateTicTacToe(ticTacToeState.value.copy(id = id, name = name)).onEach { result ->
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