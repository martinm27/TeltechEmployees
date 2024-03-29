package com.teltech.employees.application

import android.app.Application
import com.teltech.employees.connectionbar.di.connectionBarModule
import com.teltech.employees.core.appconfig.AnalyticsAppConfig
import com.teltech.employees.core.appconfig.CrashlyticsAppConfig
import com.teltech.employees.core.appconfig.RxJavaErrorHandlingAppConfig
import com.teltech.employees.core.appconfig.TimberAppConfig
import com.teltech.employees.core.di.coreModule
import com.teltech.employees.coreui.di.coreUiModule
import com.teltech.employees.details.di.detailsModule
import com.teltech.employees.di.appModule
import com.teltech.employees.employeeslib.di.employeesLibModule
import com.teltech.employees.imagelib.di.imageLibModule
import com.teltech.employees.master.di.masterModule
import com.teltech.employees.navigation.di.navigationModule
import com.teltech.employees.network.di.networkModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TeltechEmployeesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()

        TimberAppConfig().configure()
        CrashlyticsAppConfig().configure()
        RxJavaErrorHandlingAppConfig().configure()
        get<AnalyticsAppConfig>().configure()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@TeltechEmployeesApplication)
            modules(
                listOf(
                    appModule(),
                    coreUiModule(),
                    connectionBarModule(),
                    detailsModule(),
                    employeesLibModule(),
                    imageLibModule(),
                    masterModule(),
                    navigationModule(),
                    networkModule(),
                    coreModule()
                )
            )
        }
    }
}
