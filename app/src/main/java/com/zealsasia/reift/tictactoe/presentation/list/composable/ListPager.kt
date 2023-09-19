package com.zealsasia.reift.tictactoe.presentation.list.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.presentation.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.utils.TicTacToeType

@Composable
fun ListPager(
    modifier: Modifier = Modifier,
    listTicTacToe: List<TicTacToe>,
) {
    LazyColumn(modifier = modifier) {
        items(listTicTacToe) { ticTacToe ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.weight(0.75f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = if (ticTacToe.ticTacToeType == TicTacToeType.FINISHED) "Winnner: " else "Turn: ",
                            fontSize = 10.sp,
                            color = Color.Blue
                        )
                        Text(text = ticTacToe.currentTurn)
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(text = ticTacToe.name)
                }
                Icon(
                    modifier = Modifier.weight(0.25f),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "play ${ticTacToe.name} button",
                    tint = Color.Blue
                )
            }
        }
    }
}