import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeVIewModel
import org.koin.androidx.compose.getViewModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun TicTacToeBoard(modifier: Modifier = Modifier, viewModel: TicTacToeVIewModel) {
    val state = viewModel.ticTacToeState.value
    FlowRow(
        modifier
            .aspectRatio(1f),
        maxItemsInEachRow = 3,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TicTacToe(state[0][0])
        TicTacToe(state[0][1])
        TicTacToe(state[0][2])
        TicTacToe(state[1][0])
        TicTacToe(state[1][1])
        TicTacToe(state[1][2])
        TicTacToe(state[2][0])
        TicTacToe(state[2][1])
        TicTacToe(state[2][2])
    }
}

@Composable
fun RowScope.TicTacToe(state: String) {
    Box(
        modifier = Modifier
            .weight(0.333f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        if(state == "X"){
            Icon(modifier = Modifier.size(64.dp), imageVector = Icons.Default.Close, contentDescription = "X")
        } else if(state == "O"){
            Icon(modifier = Modifier.size(64.dp), imageVector = Icons.Default.AddCircle, contentDescription = "X")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeBoardPreview() {
    TicTacToeBoard(viewModel = getViewModel())
}