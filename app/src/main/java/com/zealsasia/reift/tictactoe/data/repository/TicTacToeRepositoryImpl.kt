package com.zealsasia.reift.tictactoe.data.repository

import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicTacToeRepositoryImpl(
    val remoteDataSource: RemoteDataSource
): TicTacToeRepository {
    override fun getTicTacToeList(ticTacToeType: TicTacToeType) = flow {
        try {
            emit(Resource.Loading())
            val posts = remoteDataSource.getTicTacToeList().map { it.toModel() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun saveTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

    override fun updateTicTacToe(ticTacToe: TicTacToe): Flow<Resource<String>> {
        TODO("Not yet implemented")
    }

}