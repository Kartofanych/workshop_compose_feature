package com.example.workshop_animations.some_feature

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun FeatureEventHandler(
    uiEvent: SharedFlow<FeatureEvent>,
    navigateToAnotherFeature: () -> Unit
) {

    LaunchedEffect(key1 = Unit) {
        uiEvent.collectLatest { event ->
            when (event) {
                is FeatureEvent.NavigateToAnotherFeature -> navigateToAnotherFeature()
            }
        }
    }
}