package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
fun BottomBar(
    modifier: Modifier,
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
): @Composable () -> Unit =
    {
        val viewModel = getViewModel<TicTacToeViewModel>()

        var openDialog by remember { mutableStateOf(false) }

        Row(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BottomButtons(viewModel, coroutineScope, bottomSheetState) {
                openDialog = true
            }
        }

        SaveDialog(
            openDialog,
            onDialogClose = {
                openDialog = false
            },
            onSaveClick = { name ->
                viewModel.saveTicTacToe(name)
            }
        )
    }
