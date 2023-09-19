package com.zealsasia.reift.tictactoe.presentation.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase

class TicTacToeVIewModel(
    val ticTacToeUseCase: TicTacToeUseCase
): ViewModel() {

    private val _ticTacToeState = mutableStateOf( initialState )
    val ticTacToeState get() = _ticTacToeState as State<List<List<String>>>

    fun setCurrentTicTacToe(state: List<List<String>>){
        _ticTacToeState.value = state
    }


    companion object{
        val initialState = listOf(
            listOf("", "", ""),
            listOf("", "", ""),
            listOf("", "", ""),
        )
    }
}