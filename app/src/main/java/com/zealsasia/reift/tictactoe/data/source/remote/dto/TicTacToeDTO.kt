package com.zealsasia.reift.tictactoe.data.source.remote.dto

import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.serialization.Serializable

@Serializable
data class TicTacToeDTO(
    val id: Int,
    val name: String,
    val ticTacToeType: String,
    val gameState : List<List<String?>>
)
