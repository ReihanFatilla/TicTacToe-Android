package com.zealsasia.reift.tictactoe.data.repository

import com.zealsasia.reift.tictactoe.data.source.local.LocalDataSource
import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicTacToeRepositoryImpl(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
): TicTacToeRepository {
    override fun getTicTacToeList(ticTacToeType: TicTacToeType) = flow {
        try {
            emit(Resource.Loading())
            val posts = remoteDataSource.getTicTacToeList().map { it.toPost() }
            emit(Resource.Success(data = posts))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

}