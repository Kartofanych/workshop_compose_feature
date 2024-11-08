package com.example.workshop_animations.some_feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

object MyRepository {

    private val list = MutableStateFlow(
        listOf(
            "one",
            "two",
            "three",
            "oneeqfeq",
            "twoefqeq",
            "threfeqfeqe",
            "onefqefqee",
            "tweqfeqfeo",
            "theqfqeree",
            "oneefqqfe",
            "tweqfeqfo",
            "theqfree",
            "oneefqqfewfewe",
            "tweqfeqewfewfo",
            "theqfefwewfree",
            "oneefqefqfe",
            "tweqfeefwqfo",
            "theqfefwqeree",
        )
    )

    fun getList() = list.asStateFlow()

    fun addItem(item: String) {
        list.update { it + item }
    }

    fun removeItem(item: String) {
        val currentList = list.value.toMutableList()
        currentList.remove(item)

        list.update { currentList }
    }
}

class FeatureViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<FeatureUiState>(FeatureUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _uiEvent = MutableSharedFlow<FeatureEvent>()
    val uiEvent = _uiEvent.asSharedFlow()

    private val repository = MyRepository

    init {
        viewModelScope.launch {
            delay(3000)
            repository.getList().collectLatest { items ->
                _uiState.update {
                    FeatureUiState.Content(items = items)
                }
            }
        }
    }

    fun onAction(action: FeatureAction) {
        when (action) {
            is FeatureAction.OnItemClick -> {
                viewModelScope.launch {
                    _uiEvent.emit(FeatureEvent.NavigateToAnotherFeature(name = action.name))
                }
            }

            is FeatureAction.AddItem -> {
                repository.addItem(action.name)
            }

            is FeatureAction.RemoveItem -> {
                repository.removeItem(action.name)
            }
        }
    }
}