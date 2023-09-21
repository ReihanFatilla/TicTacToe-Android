package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import com.zealsasia.reift.tictactoe.utils.Utils
import org.koin.androidx.compose.getViewModel

@Composable
fun WinnerLine(){

    val viewModel = getViewModel<TicTacToeViewModel>()
    val state by viewModel.ticTacToeState.collectAsState()

    if (state.ticTacToeType == TicTacToeType.FINISHED) {
        val winningCombination = Utils.getWinningCombination(state.gameState)
        if (winningCombination != null) {
            val (startRow, startColumn) = winningCombination.start
            val (endRow, endColumn) = winningCombination.end

            Canvas(modifier = Modifier.fillMaxSize()) {
                val cellSize = size.width / 3 // Assuming a 3x3 board
                val startX = startColumn * cellSize + cellSize / 2
                val startY = startRow * cellSize + cellSize / 2
                val endX = endColumn * cellSize + cellSize / 2
                val endY = endRow * cellSize + cellSize / 2

                drawLine(
                    color = if(state.currentTurn == "O") Color.Blue else Color.Red,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = 32f
                )
            }
        }
    }
}