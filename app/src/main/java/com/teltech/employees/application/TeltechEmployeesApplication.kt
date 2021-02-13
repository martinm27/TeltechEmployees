package com.teltech.employees.application

import android.app.Application
import com.teltech.employees.core.appconfig.RxJavaErrorHandlingAppConfig
import com.teltech.employees.core.appconfig.TimberAppConfig
import com.teltech.employees.core.di.threadingModule
import com.teltech.employees.coreui.di.coreUiModule
import com.teltech.employees.details.di.detailsModule
import com.teltech.employees.di.appModule
import com.teltech.employees.employeeslib.di.employeesLibModule
import com.teltech.employees.imagelib.di.imageLibModule
import com.teltech.employees.master.di.masterModule
import com.teltech.employees.navigation.navigationModule
import com.teltech.employees.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TeltechEmployeesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        TimberAppConfig().configure()
        RxJavaErrorHandlingAppConfig().configure()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TeltechEmployeesApplication)
            modules(
                listOf(
                    appModule(),
                    coreUiModule(),
                    detailsModule(),
                    employeesLibModule(),
                    imageLibModule(),
                    masterModule(),
                    navigationModule(),
                    networkModule(),
                    threadingModule()
                )
            )
        }
    }
}
