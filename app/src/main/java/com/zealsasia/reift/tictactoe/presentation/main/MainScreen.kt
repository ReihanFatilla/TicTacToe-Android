import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.presentation.common.StateDialog
import com.zealsasia.reift.tictactoe.presentation.list.ListScreen
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.presentation.main.composable.BottomBar
import com.zealsasia.reift.tictactoe.presentation.main.composable.TopBar
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val state by getViewModel<TicTacToeViewModel>().ticTacToeState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetContent = { ListScreen(coroutineScope = coroutineScope) }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = TopBar(modifier, title = state.name.orEmpty()),
            bottomBar = BottomBar(
                modifier,
                bottomSheetState = bottomSheetState,
                coroutineScope = coroutineScope
            ),
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                WinnerOrTurnText(state, modifier)
                TicTacToeBoard(modifier.align(Alignment.Center))
            }
            StateDialog()
        }
    }
}

@Composable
fun WinnerOrTurnText(state: TicTacToe, modifier: Modifier = Modifier) {
    val viewModel =  getViewModel<TicTacToeViewModel>()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Text(
            text = if(viewModel.isFinished){
                "Winner:"
            } else {
                "Turn:"
            }
        )
        if (state.currentTurn == "X") {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "X"
            )
        } else if (state.currentTurn == "O") {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.Favorite,
                contentDescription = "X"
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen(Modifier.fillMaxWidth())
}