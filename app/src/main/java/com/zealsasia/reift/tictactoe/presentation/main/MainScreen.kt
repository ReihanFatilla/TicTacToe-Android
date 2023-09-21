import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.presentation.dialog.StateDialog
import com.zealsasia.reift.tictactoe.presentation.list.ListScreen
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.presentation.main.composable.BottomBar
import com.zealsasia.reift.tictactoe.presentation.main.composable.TopBar
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
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
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = if(state.ticTacToeType  == TicTacToeType.FINISHED){
                "Winner:"
            } else {
                "Turn:"
            },
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        if (state.currentTurn == "X") {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.Clear,
                contentDescription = "X",
                tint = Color.Red
            )
        } else if (state.currentTurn == "O") {
            Card(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                border = BorderStroke(10.dp, Color.Blue),
                content = {}
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen(Modifier.fillMaxWidth())
}