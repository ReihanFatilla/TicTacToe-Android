package com.zealsasia.reift.tictactoe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.ui.theme.TicTacToeAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TicTacToeAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(Modifier.fillMaxWidth())
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(modifier = modifier.padding(horizontal = 20.dp), title = {
                Text(text = "Tic Tac Toe", textAlign = TextAlign.Center, modifier = modifier)
            })
        },
        bottomBar = {
            Row(modifier = modifier.padding(horizontal = 20.dp), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(modifier = Modifier.weight(0.5f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    onClick = { /*TODO*/ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "List")
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(imageVector = Icons.Default.List, contentDescription = "Show List button")
                    }
                }
                Button(modifier = Modifier.weight(0.25f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Save button")
                }
                Button(modifier = Modifier.weight(0.25f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = "reset button")
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TicTacToeAndroidTheme {
        MainScreen(Modifier.fillMaxWidth())
    }
}