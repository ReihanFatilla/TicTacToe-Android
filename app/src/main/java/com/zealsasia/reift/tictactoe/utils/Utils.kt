package com.zealsasia.reift.tictactoe.utils

import android.content.Context
import android.provider.Settings
import java.util.UUID

object Utils {
    fun generateUserToken(): String {
        val uuid = UUID.randomUUID()
//        return uuid.toString()
        return "abc123"
    }

    fun isGameFinished(board: Array<ArrayList<String>>): Boolean {
        for (i in 0 until 3) {
            if (board[i][0] != "" && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true
            if (board[0][i] != "" && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true
        }
        return board[0][0] != "" && board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                board[0][2] != "" && board[0][2] == board[1][1] && board[1][1] == board[2][0]
    }


    fun findTurn(gameState: Array<ArrayList<String>>): String {
        var xTotal = 0
        var oTotal = 0

        gameState.forEach {
            it.forEach {
                if (it == "X") {
                    xTotal++
                } else if (it == "O") {
                    oTotal++
                }
            }
        }

        return if(xTotal > oTotal) "O" else "X"
    }
}