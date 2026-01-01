package com.example.month4.ui.fragment.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.month4.domain.repository.CartRepository
import com.example.month4.domain.usecase.CheckoutUseCase
import com.example.month4.domain.usecase.ClearCartUseCase
import com.example.month4.domain.usecase.GetCartItemsUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(
    getCartItemsUseCase: GetCartItemsUseCase,
    private val checkoutUseCase: CheckoutUseCase,
    private val clearCartUseCase: ClearCartUseCase
) : ViewModel() {

    val items = getCartItemsUseCase().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val total = items.map { list ->
        list.sumOf {
            val price = it.product.price
            price * it.quantity
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0.0)

    fun checkout() {
        viewModelScope.launch {
            val result = checkoutUseCase()

            result.onSuccess {
                clearCartUseCase()
            }
        }
    }
}