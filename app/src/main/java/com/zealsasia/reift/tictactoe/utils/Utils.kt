package com.zealsasia.reift.tictactoe.utils

import android.content.Context
import android.provider.Settings
import java.util.UUID

object Utils {
    fun generateUserToken(): String {
        val uuid = UUID.randomUUID()
        return uuid.toString()
    }

    fun findTurn(gameState: List<List<String>>): String {
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