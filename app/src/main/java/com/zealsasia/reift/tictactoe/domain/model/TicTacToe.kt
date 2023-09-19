package com.zealsasia.reift.tictactoe.domain.model

import com.zealsasia.reift.tictactoe.utils.TicTacToeType

data class TicTacToe(
    val id: Int,
    val name: String,
    val ticTacToeType: TicTacToeType,
    val currentTurn: String,
    val gameState : List<List<String>>
)
