package com.zealsasia.reift.tictactoe.presentation.common

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun LoadingAndSuccessDialog() {
    val viewModel = getViewModel<TicTacToeViewModel>()
    if (viewModel.message == "loading") {
        AlertDialog(
            onDismissRequest = { },
            title = { Text("Loading...") },
            text = { Text("Please wait while we save your Game.") },
            confirmButton = {},
        )
    } else if (viewModel.message != null) {
        LaunchedEffect(Unit) {
            delay(1000)
            viewModel.message = null
        }
        AlertDialog(
            onDismissRequest = {

            },
            title = { Text("Success!") },
            text = { Text(viewModel.message.orEmpty()) },
            confirmButton = {},
        )
    }
}
