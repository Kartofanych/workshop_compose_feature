package com.example.workshop_animations.some_feature

import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun FeatureEntry(navController: NavController) {

    val viewModel = viewModel(modelClass = FeatureViewModel::class)

    FeatureEventHandler(
        uiEvent = viewModel.uiEvent,
        navigateToAnotherFeature = { navController.navigate("another_feature") }
    )

    FeatureScreen(
        uiState = viewModel.uiState.collectAsStateWithLifecycle().value,
        onAction = viewModel::onAction
    )
}