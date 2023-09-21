package com.zealsasia.reift.tictactoe.data.source.remote.api

import com.zealsasia.reift.tictactoe.BuildConfig
import com.zealsasia.reift.tictactoe.data.source.remote.dto.PostUpdateDTO
import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO

interface TicTacToeApi {

    companion object {
        private val BASE_URL = "http://${BuildConfig.BASE_URL}:8000/api"
        val GET_POST_URL = "$BASE_URL/tictactoe"
        val UPDATE_URL: (String) -> String = {"$BASE_URL/tictactoe/$it"}
    }
    suspend fun getTicTacToeList(): List<TicTacToeDTO>
    suspend fun saveTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO
    suspend fun updateTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO
}