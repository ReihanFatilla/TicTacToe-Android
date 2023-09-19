package com.zealsasia.reift.tictactoe.presentation.main

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.utils.Utils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

class TicTacToeViewModel(
    val ticTacToeUseCase: TicTacToeUseCase,
) : ViewModel() {

    private val _ticTacToeState = MutableStateFlow(initialState)
    val ticTacToeState get() = _ticTacToeState as StateFlow<Array<ArrayList<String>>>

    private val _currentTurn = mutableStateOf("X")
    val currentTurn get() = _currentTurn as State<String>
    val isFinished = mutableStateOf(false)
    val name = mutableStateOf("Tic Tac Toe")

    fun setOnClickState(row: Int, column: Int) {
        if (ticTacToeState.value[row][column] != "" || isFinished.value) return
        val currentState = ticTacToeState.value.copyOf()
        currentState[row][column] = _currentTurn.value
        _ticTacToeState.update {
            currentState
        }
        if(Utils.isGameFinished(currentState)){
            isFinished.value = true
        }
        _currentTurn.value = if (currentTurn.value == "X") "O" else "X"
        name.value = name.value + "o"
    }

    fun setCurrentTicTacToe(ticTacToe: TicTacToe) {
        _ticTacToeState.update {
            ticTacToe.gameState
        }
        _currentTurn.value = ticTacToe.currentTurn
        name.value = ticTacToe.name
    }

    companion object {
        val initialState = arrayOf(
            arrayListOf("X", "O", "X"),
            arrayListOf("", "", ""),
            arrayListOf("", "", ""),
        )
    }
}