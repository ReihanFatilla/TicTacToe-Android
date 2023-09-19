package com.zealsasia.reift.tictactoe.data.source.remote.api

import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class TicTacToeApiImpl(private val httpClient: HttpClient): TicTacToeApi {
    override suspend fun getTicTacToeList(): List<TicTacToeDTO> {
        return httpClient.get { url(TicTacToeApi.GET_URL) }
    }
}