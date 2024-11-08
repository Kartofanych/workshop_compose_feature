package com.example.workshop_animations.some_feature

sealed interface FeatureEvent {

    class NavigateToAnotherFeature(val name: String) : FeatureEvent
}