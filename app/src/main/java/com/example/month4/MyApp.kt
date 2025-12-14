package com.example.month4

import android.app.Application
import com.example.month4.data.repository.ProductRepository
import com.example.month4.ui.fragment.detail.DetailsViewModel
import com.example.month4.ui.fragment.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

class MyApp : Application() {

    val appModule = module {
        single { ProductRepository() }
        viewModel { ListViewModel(get()) }
        viewModel { DetailsViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}
