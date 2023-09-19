package com.zealsasia.reift.tictactoe.di

import com.zealsasia.reift.tictactoe.data.repository.TicTacToeRepositoryImpl
import com.zealsasia.reift.tictactoe.data.source.remote.RemoteDataSource
import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApi
import com.zealsasia.reift.tictactoe.data.source.remote.api.TicTacToeApiImpl
import com.zealsasia.reift.tictactoe.domain.repository.TicTacToeRepository
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseCase
import com.zealsasia.reift.tictactoe.domain.usecase.TicTacToeUseInteractor
import com.zealsasia.reift.tictactoe.presentation.list.ListViewModel
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeVIewModel
import com.zealsasia.reift.tictactoe.utils.Utils
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ticTacToeModule = module {
    single<TicTacToeRepository> { TicTacToeRepositoryImpl(get()) }
    factory <TicTacToeUseCase> { TicTacToeUseInteractor(get()) }
    viewModel { ListViewModel(get()) }
    viewModel { TicTacToeVIewModel(get()) }
}

val networkModule = module {
    single {
        HttpClient(Android){
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                level = LogLevel.ALL
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                header("Device-Token", Utils.generateUserToken())
                accept(ContentType.Application.Json)
            }
        }
    }
    single<TicTacToeApi> {
        TicTacToeApiImpl(get())
    }
}

val dataSourceModule = module {
    single { RemoteDataSource(get()) }
}