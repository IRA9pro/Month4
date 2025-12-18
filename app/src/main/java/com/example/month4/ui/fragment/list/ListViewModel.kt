package com.example.month4.ui.fragment.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.month4.domain.models.Product
import com.example.month4.domain.usecase.GetProductsUseCase
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<UIState<List<Product>>>(UIState.Loading)
    val state: StateFlow<UIState<List<Product>>> = _state.asStateFlow()

    init {
        loadProduct()
    }

    fun loadProduct() {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val products = getProductsUseCase()
                _state.value = UIState.Success(products)
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "")
            }
        }
    }
}