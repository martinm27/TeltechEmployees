package com.teltech.employees.application

import android.app.Application
import com.teltech.employees.di.appModule
import com.teltech.employees.navigation.navigationModule
import com.teltech.employees.network.di.networkModule
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
                    appModule(),
                    navigationModule(),
                    networkModule(),
                )
            )
        }
    }

}