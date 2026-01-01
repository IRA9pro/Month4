package com.example.month4.domain.di

import com.example.month4.domain.usecase.AddToCartUseCase
import com.example.month4.domain.usecase.CheckoutUseCase
import com.example.month4.domain.usecase.ClearCartUseCase
import com.example.month4.domain.usecase.GetCartItemsUseCase
import com.example.month4.domain.usecase.GetProductUseCase
import com.example.month4.domain.usecase.GetProductsUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(repository = get()) }
    factory { GetProductUseCase(repository = get()) }

    factory { GetCartItemsUseCase(get()) }
    factory { CheckoutUseCase(get()) }
    factory { ClearCartUseCase(get()) }
    factory { AddToCartUseCase(get()) }
}