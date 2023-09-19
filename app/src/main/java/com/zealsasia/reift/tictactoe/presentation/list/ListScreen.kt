package com.zealsasia.reift.tictactoe.presentation.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.zealsasia.reift.tictactoe.presentation.list.composable.ListPager
import com.zealsasia.reift.tictactoe.presentation.list.composable.ListTabRow
import com.zealsasia.reift.tictactoe.utils.Resource
import com.zealsasia.reift.tictactoe.utils.TicTacToeType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    coroutineScope: CoroutineScope,
){
    val viewModel = getViewModel<ListViewModel>()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f,
    )

    Scaffold(
        topBar = ListTabRow(pagerState = pagerState) { selectedIndex ->
            coroutineScope.launch {
                pagerState.animateScrollToPage(selectedIndex)
            }
        }
    ) { paddingValues ->
        HorizontalPager(
            modifier = modifier.fillMaxSize().padding(paddingValues),
            pageCount = 2,
            state = pagerState,
            userScrollEnabled = true,
        ) { index ->
            when (index) {
                0 -> {
                    val value = viewModel.onGoingState.value
                    when(value){
                        is Resource.Success -> {
                            ListPager(
                                modifier = modifier.fillMaxWidth(),
                                listTicTacToe = value.data.orEmpty().filter { it.ticTacToeType == TicTacToeType.ONGOING })
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {

                        }
                    }
                }
                1 -> {
                    val value = viewModel.finishedState.value
                    when(value){
                        is Resource.Success -> {
                            ListPager(
                                modifier = modifier.fillMaxWidth(),
                                listTicTacToe = value.data.orEmpty().filter { it.ticTacToeType == TicTacToeType.FINISHED })
                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Error -> {

                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListScreenPreview(){
    ListScreen(
        modifier = Modifier.fillMaxSize(),
        coroutineScope = rememberCoroutineScope(),
    )
}