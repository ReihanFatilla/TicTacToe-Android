package com.zealsasia.reift.tictactoe.domain.usecase

import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.flow.Flow

class TicTacToeUseInteractor(val ticTacToeRepository: TicTacToeRepository): TicTacToeUseCase {
    override fun getTicTacToeList(ticTacToeType: TicTacToeType): Flow<Resource<List<TicTacToe>>> {
        return ticTacToeRepository.getTicTacToeList(ticTacToeType)
    }

    override fun saveTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>> {
        return ticTacToeRepository.saveTicTacToe(ticTacToe)
    }

    override fun updateTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>> {
        return ticTacToeRepository.saveTicTacToe(ticTacToe)
    }
}