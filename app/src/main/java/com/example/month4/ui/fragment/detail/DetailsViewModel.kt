package com.example.month4.ui.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.month4.domain.models.Product
import com.example.month4.domain.usecase.GetProductUseCase
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getProductUseCase: GetProductUseCase) : ViewModel() {

    private val _state = MutableStateFlow<UIState<Product>>(UIState.Loading)
    val state: StateFlow<UIState<Product>> = _state.asStateFlow()

    fun loadItemProduct(productId: Int) {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                _state.value = UIState.Success(getProductUseCase(productId))
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "")
            }
        }
    }
}