package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun BottomBar(
    modifier: Modifier,
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope
): @Composable () -> Unit =
    {
        Row(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(modifier = Modifier.weight(0.5f),
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