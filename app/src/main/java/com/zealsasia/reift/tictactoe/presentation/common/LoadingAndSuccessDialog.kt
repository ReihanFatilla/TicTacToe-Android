package com.zealsasia.reift.tictactoe.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.utils.Resource
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun LoadingAndSuccessDialog() {
    val viewModel = getViewModel<TicTacToeViewModel>()
    val message = viewModel.postUpdateState?.data
    when (viewModel.postUpdateState) {
        is Resource.Success -> {
            LaunchedEffect(Unit) {
                delay(1000)
                viewModel.postUpdateState = null
            }
            Dialog(onDismissRequest = { }) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier,
                ) {
                    Column(
                        Modifier
                            .background(Color.White)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            modifier = Modifier.size(72.dp),
                            contentDescription = "success icon",
                            tint = Color.Green
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = message.orEmpty(), textAlign = TextAlign.Center)
                    }
                }
            }
        }

        is Resource.Loading -> {
            Dialog(onDismissRequest = { }) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier,
                ) {
                    Column(
                        Modifier
                            .background(Color.White)
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Loading.. Please wait..",
                            Modifier
                                .padding(8.dp), textAlign = TextAlign.Center
                        )

                        CircularProgressIndicator(
                            strokeWidth = 4.dp,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(8.dp)
                        )
                    }
                }
            }

        }

        is Resource.Error -> {
            LaunchedEffect(Unit) {
                delay(1000)
                viewModel.postUpdateState = null
            }
            Dialog(onDismissRequest = { }) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier,
                ) {
                    Column(
                        Modifier
                            .background(Color.White)
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            modifier = Modifier.size(72.dp),
                            contentDescription = "error icon",
                            tint = Color.Green
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(text = message.orEmpty(), textAlign = TextAlign.Center)
                    }
                }
            }
        }

        else -> {}
    }
}
