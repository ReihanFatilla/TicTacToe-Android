package com.zealsasia.reift.tictactoe.presentation.main.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
fun topBar(modifier: Modifier): @Composable () -> Unit = {
    TopAppBar(modifier = modifier.padding(horizontal = 20.dp), title = {
        Text(text = "Tic Tac Toe", textAlign = TextAlign.Center, modifier = modifier)
    })
}