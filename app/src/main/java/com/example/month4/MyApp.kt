package com.example.month4

import android.app.Application
import com.example.month4.data.di.dataModule
import com.example.month4.domain.di.domainModule
import com.example.month4.ui.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(
                dataModule,
                domainModule,
                uiModule
            )
        }
    }
}
