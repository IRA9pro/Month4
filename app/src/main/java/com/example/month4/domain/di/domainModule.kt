package com.example.month4.domain.di

import com.example.month4.domain.usecase.GetProductUseCase
import com.example.month4.domain.usecase.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetProductsUseCase(repository = get()) }
    factory { GetProductUseCase(repository = get()) }
}