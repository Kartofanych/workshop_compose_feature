package com.example.workshop_animations.some_feature

sealed interface FeatureUiState {

    object Loading : FeatureUiState

    data class Content(val items: List<String>) : FeatureUiState
}