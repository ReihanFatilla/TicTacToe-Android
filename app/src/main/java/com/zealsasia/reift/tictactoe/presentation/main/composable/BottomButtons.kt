package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RowScope.BottomButtons(
    viewModel: TicTacToeViewModel,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
) {
    Button(modifier = Modifier.weight(0.5f).fillMaxHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        onClick = {
            coroutineScope.launch {
                bottomSheetState.show()
            }
        }) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "List")
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                imageVector = Icons.Default.List,
                contentDescription = "Show List button"
            )
        }
    }
    Button(modifier = Modifier.weight(0.25f).fillMaxHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        onClick = {
            viewModel.resetTicTacToe()
        }) {
        Icon(imageVector = Icons.Default.Refresh, contentDescription = "reset button")
    }
    Button(modifier = Modifier.weight(0.25f).fillMaxHeight(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
        onClick = {
            if(!viewModel.checkIfGameStateEmpty()){
                viewModel.openDialog = true
            }
        }) {
        Icon(imageVector = Icons.Default.Done, contentDescription = "Save button")
    }
}