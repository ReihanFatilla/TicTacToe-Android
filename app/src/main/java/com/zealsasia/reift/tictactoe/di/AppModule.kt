package com.zealsasia.reift.tictactoe.di

import com.zealsasia.reift.tictactoe.data.repository.TicTacToeRepositoryImpl
import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApi
import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApiImpl
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseInteractor
import com.zealsasia.reift.tictactoe.presentation.TicTacToeViewModel
import io.ktor.client.HttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticTacToeModule = module {
    single<TicTacToeRepository> { TicTacToeRepositoryImpl(get()) }
    single<TicTacToeUseCase> { TicTacToeUseInteractor(get()) }
    viewModel { TicTacToeViewModel(get()) }
}

val networkModule = module {
    single {
        HttpClient()
    }
    single<TicTacToeApi> {
        TicTacToeApiImpl(get())
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
}