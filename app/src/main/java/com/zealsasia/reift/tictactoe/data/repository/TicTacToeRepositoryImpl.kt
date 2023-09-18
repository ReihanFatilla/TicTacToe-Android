package com.zealsasia.reift.tictactoe.data.repository

import com.zealsasia.reift.tictactoe.data.source.local.LocalDataSource
import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository

class TicTacToeRepositoryImpl(
    val localDataSource: LocalDataSource,
    val remoteDataSource: RemoteDataSource
): TicTacToeRepository {
}