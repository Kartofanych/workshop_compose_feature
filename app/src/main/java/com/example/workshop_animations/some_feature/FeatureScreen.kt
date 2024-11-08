package com.example.workshop_animations.some_feature

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.UUID
import kotlin.random.Random
import kotlin.reflect.KFunction1

@Composable
fun FeatureScreen(uiState: FeatureUiState, onAction: KFunction1<FeatureAction, Unit>) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        when (uiState) {
            is FeatureUiState.Content -> {
                FeatureContent(Modifier.padding(top = it.calculateTopPadding()), uiState, onAction)
            }

            FeatureUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun FeatureContent(
    modifier: Modifier,
    uiState: FeatureUiState.Content,
    onAction: (FeatureAction) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val firstVisibleItem = remember { derivedStateOf { lazyListState.firstVisibleItemIndex } }
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Мои дела",
            color = Color.Black,
            fontSize = 30.sp,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.White)
        )

        AnimatedVisibility(visible = firstVisibleItem.value < 3) {
            Text(
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(Color.White),
                text = "Всего ${uiState.items.size} дел"
            )
        }

        LazyColumn(
            state = lazyListState
        ) {
            items(uiState.items, key = { UUID.randomUUID() }) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .clickable {
                            val random = Random.nextInt(0, 3)
                            when (random) {
                                0 -> onAction(FeatureAction.OnItemClick(item))
                                1 -> onAction(FeatureAction.RemoveItem(item))
                                2 -> onAction(FeatureAction.AddItem(item))
                            }
                        }
                ) {
                    Text(text = item)
                }
            }
        }
    }
}
