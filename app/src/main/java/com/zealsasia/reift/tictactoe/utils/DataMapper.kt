package com.zealsasia.reift.tictactoe.utils

import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe

fun TicTacToeDTO.toModel(): TicTacToe {
    return TicTacToe(
        id = id,
        name = name,
        ticTacToeType = if(ticTacToeType == "ongoing") TicTacToeType.ONGOING else TicTacToeType.FINISHED,
        currentTurn = Utils.findTurn(gameState.map()),
        gameState = gameState.map()
    )
}

fun List<List<String?>>.map(): List<List<String>> {
    return map { it.map { it.orEmpty() } }
}