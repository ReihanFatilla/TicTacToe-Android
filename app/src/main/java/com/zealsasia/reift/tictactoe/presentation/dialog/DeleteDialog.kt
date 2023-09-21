package com.zealsasia.reift.tictactoe.presentation.dialog

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

@Composable
fun DeleteDialog(name: String, openDialog: Boolean, onDeleteClick: () -> Unit, onCloseDialog: () -> Unit){
    if(openDialog){
        AlertDialog(
            shape = RoundedCornerShape(10.dp),
            onDismissRequest = {
                onCloseDialog()
            },
            title = {
                Text(text = "Do you want to Delete $name?")
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
                            onCloseDialog()
                        }) {
                            Text(
                                "Cancel",
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        TextButton(onClick = {
                            onDeleteClick()
                        }) {
                            Text(
                                "Delete",
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