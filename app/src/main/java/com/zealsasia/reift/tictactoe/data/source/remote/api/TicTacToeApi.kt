package com.zealsasia.reift.tictactoe.data.source.remote.api

import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO

interface TicTacToeApi {

    companion object {
        private const val BASE_URL = "http://127.0.0.1:8000"
        const val GET_URL = "$BASE_URL/tictactoe"
    }
    suspend fun getTicTacToeList(): List<TicTacToeDTO>
}