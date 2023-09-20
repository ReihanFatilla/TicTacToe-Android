package com.zealsasia.reift.tictactoe.data.source.remote.api

import com.zealsasia.reift.tictactoe.data.source.remote.dto.PostUpdateDTO
import com.zealsasia.reift.tictactoe.data.source.remote.dto.TicTacToeDTO
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

class TicTacToeApiImpl(private val httpClient: HttpClient): TicTacToeApi {
    override suspend fun getTicTacToeList(): List<TicTacToeDTO> {
        return httpClient.get {
            url(TicTacToeApi.GET_URL)
        }
    }

    override suspend fun saveTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO {
        return httpClient.post {
            url(TicTacToeApi.GET_URL)
            contentType(ContentType.Application.Json)
            body = ticTacToeDTO
        }
    }

    override suspend fun updateTicTacToe(ticTacToeDTO: TicTacToeDTO): PostUpdateDTO {
        return httpClient.patch {
            url(TicTacToeApi.GET_URL)
            contentType(ContentType.Application.Json)
            body = ticTacToeDTO
        }
    }
}