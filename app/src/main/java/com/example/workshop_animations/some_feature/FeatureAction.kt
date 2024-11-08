package com.example.workshop_animations.some_feature

sealed interface FeatureAction {

    class OnItemClick(val name: String) : FeatureAction

    class AddItem(val name: String) : FeatureAction

    class RemoveItem(val name: String) : FeatureAction
}