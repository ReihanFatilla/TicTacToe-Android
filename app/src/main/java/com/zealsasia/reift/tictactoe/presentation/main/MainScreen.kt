import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.presentation.list.ListScreen
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import com.zealsasia.reift.tictactoe.presentation.main.composable.BottomBar
import com.zealsasia.reift.tictactoe.presentation.main.composable.TopBar
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {

    val titleState by getViewModel<TicTacToeViewModel>().ticTacToeState.collectAsState()

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
            topBar = TopBar(modifier, title = titleState.name),
            bottomBar = BottomBar(
                modifier,
                bottomSheetState = bottomSheetState,
                coroutineScope = coroutineScope
            )
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
            ) {
                TicTacToeBoard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MainScreen(Modifier.fillMaxWidth())
}