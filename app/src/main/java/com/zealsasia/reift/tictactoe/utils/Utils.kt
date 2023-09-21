package com.zealsasia.reift.tictactoe.utils

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings.Secure


object Utils {
    @SuppressLint("HardwareIds")
    fun generateUserToken(context: Context): String? {
        return Secure.getString(context.contentResolver, Secure.ANDROID_ID)
    }

    fun List<List<String>>.isGameFinished(): Boolean {
        for (i in 0 until 3) {
            if (this[i][0] != "" && this[i][0] == this[i][1] && this[i][1] == this[i][2]) return true
            if (this[0][i] != "" && this[0][i] == this[1][i] && this[1][i] == this[2][i]) return true
        }
        return this[0][0] != "" && this[0][0] == this[1][1] && this[1][1] == this[2][2] ||
                this[0][2] != "" && this[0][2] == this[1][1] && this[1][1] == this[2][0]
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

    fun List<List<String?>>.map(): List<List<String>> {
        return map { it.map { it.orEmpty() } }
    }
}