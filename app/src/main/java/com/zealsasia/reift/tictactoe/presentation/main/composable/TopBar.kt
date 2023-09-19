package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
fun TopBar(modifier: Modifier, title: String): @Composable () -> Unit = {
    TopAppBar(modifier = modifier.padding(horizontal = 20.dp), title = {
        Text(text = title, textAlign = TextAlign.Center, modifier = modifier)
    })
}