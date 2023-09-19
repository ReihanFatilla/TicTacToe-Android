package com.zealsasia.reift.tictactoe.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zealsasia.reift.tictactoe.domain.model.TicTacToe
import com.zealsasia.reift.tictactoe.presentation.list.composable.ListPager
import com.zealsasia.reift.tictactoe.presentation.list.composable.ListTabRow
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(modifier: Modifier = Modifier){
    val listTicTacToe = TicTacToe.generateDummy()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    )

    Scaffold(
        topBar = ListTabRow(pagerState = pagerState) { selectedIndex ->
            scope.launch {
                pagerState.animateScrollToPage(selectedIndex)
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            modifier = modifier.padding(paddingValues),
            pageCount = 2,
            state = pagerState,
            userScrollEnabled = true,
        ) { index ->
            when (index) {
                0 -> ListPager(modifier = modifier.fillMaxWidth(), listTicTacToe = listTicTacToe.filter { it.ticTacToeType == TicTacToeType.ONGOING })
                1 -> ListPager(modifier = modifier.fillMaxWidth(), listTicTacToe = listTicTacToe.filter { it.ticTacToeType == TicTacToeType.FINISHED })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    ListScreen(
        modifier = Modifier.fillMaxSize()
    )
}