package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SaveDialog(onSaveClick: (String) -> Unit) {
    var name by remember { mutableStateOf("") }

    val viewModel = getViewModel<TicTacToeViewModel>()

    if (viewModel.openDialog) {
        AlertDialog(
            shape = RoundedCornerShape(10.dp),
            onDismissRequest = {
                viewModel.openDialog = false
            },
            title = {
                Text(text = "Do you want to save your Current Game Progress?")
            },
            text = {
                Spacer(modifier = Modifier.height(24.dp))
                TextField(
                    value = name,
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
                    maxLines = 1,
                    onValueChange = {
                        if (it.length <= 32) name = it
                    },
                    label = {
                        Text(
                            "Saved Name (optional)",
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Normal,
                            fontSize = 11.sp
                        )
                    }
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 12.dp),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        TextButton(onClick = {
                            viewModel.openDialog = false
                        }) {
                            Text(
                                "Not Now",
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        TextButton(onClick = {
                            onSaveClick(name)
                        }) {
                            Text(
                                if (viewModel.isUpdateMode) "Update" else "Save",
                                color = Color.Blue,
                                fontWeight = FontWeight.ExtraBold,
                            )
                        }
                    }
                }
            }
        )
    }
}