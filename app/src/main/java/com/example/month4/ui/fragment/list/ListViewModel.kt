package com.example.month4.ui.fragment.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.month4.data.model.ProductDto
import com.example.month4.data.repository.ProductRepository
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel(private val repositoryItem: ProductRepository) : ViewModel() {
    private val repository = ProductRepository()

    private val _state = MutableStateFlow<UIState<List<ProductDto>>>(UIState.Loading)
    val state: StateFlow<UIState<List<ProductDto>>> = _state.asStateFlow()

    init {
        loadProduct()
    }

    fun loadProduct() {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                val products = repository.getProducts()
                _state.value = UIState.Success(products)
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "")
            }
        }
    }
}