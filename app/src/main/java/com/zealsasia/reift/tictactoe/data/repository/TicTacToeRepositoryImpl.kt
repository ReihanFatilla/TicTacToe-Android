package com.zealsasia.reift.tictactoe.data.repository

import android.util.Log
import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.toDTO
import com.zealsasia.reift.tictactoe.utils.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TicTacToeRepositoryImpl(
    val remoteDataSource: RemoteDataSource
): TicTacToeRepository {
    override fun getTicTacToeList() = flow {
        try {
            emit(Resource.Loading())
            val list = remoteDataSource.getTicTacToeList().map { it.toModel() }
            emit(Resource.Success(data = list))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun saveTicTacToe(ticTacToe: TicTacToe) = flow {
        try {
            emit(Resource.Loading())
            val message = remoteDataSource.saveTicTacToe(ticTacToe.toDTO()).message
            Log.i("saveTicTacToesss", "message: $message")
            emit(Resource.Success(data = message))
        } catch (e: Exception){
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

    override fun updateTicTacToe(ticTacToe: TicTacToe) = flow {
        try {
            emit(Resource.Loading())
            val message = remoteDataSource.updateTicTacToe(ticTacToe.toDTO()).message
            emit(Resource.Success(data = message))
        } catch (e: Exception){
            emit(Resource.Error(message = e.message ?: "Error"))
        }
    }

}