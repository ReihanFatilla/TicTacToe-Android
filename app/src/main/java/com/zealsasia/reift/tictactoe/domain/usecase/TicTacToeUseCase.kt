package com.zealsasia.reift.tictactoe.domain.usecase

import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.flow.Flow

interface TicTacToeUseCase {
    fun getTicTacToeList(ticTacToeType: TicTacToeType): Flow<Resource<List<TicTacToe>>>
}