package com.mj.kmjhomework

import android.app.Application
import com.mj.kmjhomework.di.beerModule
import com.mj.kmjhomework.di.mainViewModelModule
import com.mj.kmjhomework.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GlobalApp : Application() {
    override fun onCreate() {
        super.onCreate()

        //DI_Module Application 시작과 함께 startKoin을 통해 인자로 넘겨준다
        startKoin {
            androidContext(this@GlobalApp)
            modules(
                networkModule,
                beerModule,
                mainViewModelModule
            )
        }
    }
}