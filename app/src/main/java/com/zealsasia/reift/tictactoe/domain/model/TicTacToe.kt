package com.zealsasia.reift.tictactoe.domain.model

import com.zealsasia.reift.tictactoe.utils.TicTacToeType

data class TicTacToe(
    val id: Int,
    val name: String,
    val ticTacToeType: TicTacToeType,
    val currentTurn: String,
    val gameState: List<List<String>>,
) {
    companion object {

        val initial = TicTacToe(
            id = 0,
            name = "Tic Tac Toe",
            ticTacToeType = TicTacToeType.ONGOING,
            currentTurn = "X",
            gameState = listOf(
                listOf("", "", ""),
                listOf("", "", ""),
                listOf("", "", ""),
            )
        )

        fun generateDummy(): ArrayList<TicTacToe> {
            return arrayListOf<TicTacToe>().also { list ->
                repeat(30) {
                    list.add(
                        TicTacToe(
                            id = it,
                            name = "Play on 1 September 2023 | 13:00 PM",
                            ticTacToeType = TicTacToeType.values().random(),
                            currentTurn = "X",
                            gameState = listOf(
                                listOf("","","O"),
                                listOf("","X","O"),
                                listOf("","","")
                            )
                        )
                    )
                }
            }
        }
    }
}
