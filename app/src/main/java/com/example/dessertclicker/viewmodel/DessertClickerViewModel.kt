package com.example.dessertclicker.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dessertclicker.data.DessertClickerState
import com.example.dessertclicker.model.Dessert
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DessertClickerViewModel : ViewModel() {

    // Using MutableStateFlow to hold the state, initialized with the default state
    private val _state = MutableStateFlow(DessertClickerState())
    val state = _state.asStateFlow()

    fun updateDessertState(desserts: List<Dessert>, index: Int) {
        viewModelScope.launch {
            val current = _state.value
            val clickedDessert = desserts[index]
            _state.emit(
                current.copy(
                    revenue = current.revenue + clickedDessert.price,
                    dessertsSold = current.dessertsSold + 1,
                    currentDessertPrice = clickedDessert.price,
                    currentDessertImageId = clickedDessert.imageId
                )
            )
        }
    }




}
