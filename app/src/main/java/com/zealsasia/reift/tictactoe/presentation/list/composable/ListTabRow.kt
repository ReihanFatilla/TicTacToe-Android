package com.zealsasia.reift.tictactoe.presentation.list.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalFoundationApi::class)
fun ListTabRow(pagerState: PagerState, onClick: (Int) -> Unit,): @Composable () -> Unit = {
    TabRow(selectedTabIndex = pagerState.currentPage, backgroundColor = Color.White) {
        listOf(
            "Ongoing",
            "Finished"
        ).forEachIndexed { index, text ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    onClick(index)
                },
                text = {
                       Text(text = text)
                },
            )
        }
    }
}