package com.zealsasia.reift.tictactoe.data.source.remote

import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApi
import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO

class RemoteDataSource(
    val ticTacToeApi: TicTacToeApi
) {
    suspend fun getTicTacToeList(): List<TicTacToeDTO> {
        return ticTacToeApi.getTicTacToeList()
    }
    suspend fun saveTicTacToe(ticTacToeDTO: TicTacToeDTO): String {
        return ticTacToeApi.saveTicTacToe(ticTacToeDTO)
    }
    suspend fun updateTicTacToe(ticTacToeDTO: TicTacToeDTO): String {
        return ticTacToeApi.updateTicTacToe(ticTacToeDTO)
    }
}