import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@OptIn(ExperimentalLayoutApi::class)
fun TicTacToeBoard(modifier: Modifier = Modifier) {
    FlowRow(
        modifier.aspectRatio(1f),
        maxItemsInEachRow = 3
    ) {
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
        TicTacToe()
    }
}

@Composable
fun RowScope.TicTacToe() {
    Box(
        modifier = Modifier
            .weight(0.333f)
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = Icons.Default.Close, contentDescription = "X")
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToeBoardPreview() {
    TicTacToeBoard()
}