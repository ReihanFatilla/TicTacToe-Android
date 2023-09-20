package com.zealsasia.reift.tictactoe.utils

import android.content.Context
import android.provider.Settings.Secure
import java.util.UUID


object Utils {
    fun generateUserToken(context: Context): String? {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }

    fun isGameFinished(board: List<List<String>>): Boolean {
        for (i in 0 until 3) {
            if (board[i][0] != "" && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true
            if (board[0][i] != "" && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true
        }
        return board[0][0] != "" && board[0][0] == board[1][1] && board[1][1] == board[2][2] ||
                board[0][2] != "" && board[0][2] == board[1][1] && board[1][1] == board[2][0]
    }


    fun findTurn(gameState: List<List<String>>, isFinished: Boolean): String {
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

        return if (xTotal > oTotal && !isFinished) "O" else "X"
    }



    // ...

    fun getWinningCombination(gameState: List<List<String>>): WinningCombination? {

        for (i in 0 until 3) {
            if (gameState[i][0] == gameState[i][1] && gameState[i][1] == gameState[i][2] && gameState[i][0].isNotEmpty()) {
                return WinningCombination(Pair(i, 0), Pair(i, 2))
            }

            if (gameState[0][i] == gameState[1][i] && gameState[1][i] == gameState[2][i] && gameState[0][i].isNotEmpty()) {
                return WinningCombination(Pair(0, i), Pair(2, i))
            }
        }

        if (gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2] && gameState[0][0].isNotEmpty()) {
            return WinningCombination(Pair(0, 0), Pair(2, 2))
        }
        if (gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0] && gameState[0][2].isNotEmpty()) {
            return WinningCombination(Pair(0, 2), Pair(2, 0))
        }

        return null
    }

}