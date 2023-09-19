package com.zealsasia.reift.tictactoe.utils

import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe

fun TicTacToeDTO.toModel(): TicTacToe {
    return TicTacToe(
        id = id,
        name = name,
        ticTacToeType = if(type == "ongoing") TicTacToeType.ONGOING else TicTacToeType.FINISHED,
        currentTurn = Utils.findTurn(game_state.map()),
        gameState = game_state.map()
    )
}

fun List<List<String?>>.map(): Array<ArrayList<String>> {
    return map { it.map { it.orEmpty() } as ArrayList<String> }.toTypedArray()
}