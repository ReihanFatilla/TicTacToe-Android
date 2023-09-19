import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zealsasia.reift.tictactoe.presentation.main.TicTacToeViewModel
import org.koin.androidx.compose.getViewModel

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun TicTacToeBoard(modifier: Modifier = Modifier) {
    val viewModel = getViewModel<TicTacToeViewModel>()
    val gameState by viewModel.ticTacToeState.collectAsState()
    FlowRow(
        modifier
            .aspectRatio(1f),
        maxItemsInEachRow = 3,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(3) { row ->
            repeat(3) { column ->
                TicTacToe(row, column, gameState) {
                    viewModel.setOnClickState(row, column)
                }
            }
        }
    }
}

@Composable
fun RowScope.TicTacToe(row: Int, column: Int, gameState: Array<ArrayList<String>>, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .weight(0.333f)
            .aspectRatio(1f)
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
            .clickable { onClick() }
        ,
        contentAlignment = Alignment.Center
    ) {
        if (gameState[row][column] == "X") {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.Close,
                contentDescription = "X"
            )
        } else if (gameState[row][column] == "O") {
            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Default.AddCircle,
                contentDescription = "X"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeBoardPreview() {
    TicTacToeBoard()
}