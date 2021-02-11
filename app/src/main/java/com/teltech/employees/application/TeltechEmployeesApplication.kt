package com.teltech.employees.application

import android.app.Application
import com.teltech.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TeltechEmployeesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TeltechEmployeesApplication)
            modules(
                listOf(
                    networkModule(),
                )
            )
        }
    }

}
