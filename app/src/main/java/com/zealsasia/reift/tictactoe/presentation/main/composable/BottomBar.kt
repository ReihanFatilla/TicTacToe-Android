package com.zealsasia.reift.tictactoe.presentation.main.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import kotlinx.coroutines.CoroutineScope
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterialApi::class)
fun BottomBar(
    modifier: Modifier,
    bottomSheetState: ModalBottomSheetState,
    coroutineScope: CoroutineScope,
): @Composable () -> Unit =
    {
        val viewModel = getViewModel<TicTacToeViewModel>()

        Row(
            modifier = modifier.padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BottomButtons(viewModel, coroutineScope, bottomSheetState)
        }

        SaveDialog(
            onSaveClick = { name ->
                if (viewModel.isUpdateMode) {
                    viewModel.updateTicTacToe(name)
                } else {
                    viewModel.saveTicTacToe(name)
                }
                viewModel.openDialog = false
            }
        )
    }
