package com.zealsasia.reift.tictactoe.data.source.remote

import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApi
import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO

class RemoteDataSource(
    val ticTacToeApi: TicTacToeApi
) {
    suspend fun getTicTacToeList(): List<TicTacToeDTO> {
        return ticTacToeApi.getTicTacToeList()
    }
    suspend fun postTicTacToeList(ticTacToeDTO: TicTacToeDTO): String {
        return ticTacToeApi.postTicTacToeList(ticTacToeDTO)
    }
    suspend fun updateTicTacToeList(ticTacToeDTO: TicTacToeDTO): String {
        return ticTacToeApi.updateTicTacToeList(ticTacToeDTO)
    }
}