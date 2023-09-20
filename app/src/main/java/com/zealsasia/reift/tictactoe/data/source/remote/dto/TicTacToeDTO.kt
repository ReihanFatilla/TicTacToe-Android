package com.zealsasia.reift.tictactoe.data.source.remote.dto

import kotlinx.serialization.Serializable


@Serializable
data class TicTacToeDTO(
    val id: Int? = null,
    val name: String? = null,
    val type: String,
    val game_state : List<List<String?>>
)
