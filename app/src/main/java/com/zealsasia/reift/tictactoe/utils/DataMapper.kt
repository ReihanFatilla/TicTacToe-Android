package com.zealsasia.reift.tictactoe.utils

import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe

fun TicTacToeDTO.toModel(): TicTacToe {
    return TicTacToe(
        id = id,
        name = name,
        ticTacToeType = if(type == "ongoing") TicTacToeType.ONGOING else TicTacToeType.FINISHED,
        currentTurn = Utils.findTurn(game_state.map(), type == "finished"),
        gameState = game_state.map()
    )
}

fun TicTacToe.toDTO(): TicTacToeDTO {
    return TicTacToeDTO(
        id = id,
        name = name,
        type = if(ticTacToeType == TicTacToeType.ONGOING) "ongoing" else "finished",
        game_state = gameState
    )
}

fun List<List<String?>>.map(): List<List<String>> {
    return map { it.map { it.orEmpty() } }
}