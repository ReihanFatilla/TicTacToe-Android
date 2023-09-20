package com.zealsasia.reift.tictactoe.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update

class TicTacToeViewModel(
    val ticTacToeUseCase: TicTacToeUseCase,
) : ViewModel() {

    private val _ticTacToeState = MutableStateFlow(TicTacToe.initial)
    val ticTacToeState get() = _ticTacToeState as StateFlow<TicTacToe>

    var isFinished by mutableStateOf(false)

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
    }

    private fun checkIfGameFinished(state: List<MutableList<String>>) {
        if (Utils.isGameFinished(state)) {
            isFinished = true
        }
    }

    fun setCurrentTicTacToe(ticTacToe: TicTacToe) {
        _ticTacToeState.update {
            ticTacToe
        }
    }

}