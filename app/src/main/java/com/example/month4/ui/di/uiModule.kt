package com.example.month4.ui.di

import com.example.month4.ui.fragment.cart.CartViewModel
import com.example.month4.ui.fragment.detail.DetailsViewModel
import com.example.month4.ui.fragment.list.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ListViewModel(getProductsUseCase = get(), get()) }
    viewModel { DetailsViewModel(getProductUseCase = get()) }
    viewModel { CartViewModel(get(), get(), get()) }
}