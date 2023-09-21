package com.zealsasia.reift.tictactoe.data.source.remote

import android.util.Log
import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApi
import com.zealsasia.reift.tictactoe.data.source.remote.dto.PostUpdateDTO
import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO

class RemoteDataSource(
    val ticTacToeApi: TicTacToeApi
) {
    suspend fun getTicTacToeList(): List<TicTacToeDTO> {
        return ticTacToeApi.getTicTacToeList()
    }
    suspend fun saveTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO {
        return ticTacToeApi.saveTicTacToe(ticTacToeDTO)
    }
    suspend fun updateTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO {
        return ticTacToeApi.updateTicTacToe(ticTacToeDTO)
    }
    suspend fun deleteTicTacToe(id: Int): PostUpdateDTO {
        return ticTacToeApi.deleteTicTacToe(id)
    }
}