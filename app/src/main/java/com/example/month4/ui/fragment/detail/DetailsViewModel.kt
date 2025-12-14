package com.example.month4.ui.fragment.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.month4.data.model.ProductDto
import com.example.month4.data.repository.ProductRepository
import com.example.month4.ui.models.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(private val repositoryItem: ProductRepository) : ViewModel() {

    private val _state = MutableStateFlow<UIState<ProductDto>>(UIState.Loading)
    val state: StateFlow<UIState<ProductDto>> = _state.asStateFlow()

    fun loadItemProduct(productId: Int) {
        viewModelScope.launch {
            _state.value = UIState.Loading
            try {
                _state.value = UIState.Success(ProductRepository().getItemProduct(productId))
            } catch (e: Exception) {
                _state.value = UIState.Error(e.message ?: "")
            }
        }
    }
}