package com.zealsasia.reift.tictactoe.domain.usecase

import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TicTacToeUseCase {
    fun getTicTacToeList(): Flow<Resource<List<TicTacToe>>>
    fun saveTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>>
    fun updateTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>>
    fun deleteTicTacToe(id: Int): Flow<Resource<String>>
}