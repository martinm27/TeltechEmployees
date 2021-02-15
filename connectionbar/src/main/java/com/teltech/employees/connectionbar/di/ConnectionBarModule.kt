package com.teltech.employees.connectionbar.di

import com.teltech.employees.connectionbar.resources.ConnectionStatusResources
import com.teltech.employees.connectionbar.resources.ConnectionStatusResourcesImpl
import com.teltech.employees.connectionbar.ui.ConnectionBarViewModel
import com.teltech.employees.connectionbar.usecase.QueryInternetConnection
import com.teltech.employees.core.di.BACKGROUND_SCHEDULER
import com.teltech.employees.core.di.MAIN_SCHEDULER
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun connectionBarModule(): Module = module {

    viewModel {
        ConnectionBarViewModel(
            get(),
            get(),
            mainThreadScheduler = get(named(MAIN_SCHEDULER)),
            backgroundScheduler = get(named(BACKGROUND_SCHEDULER)),
            routingActionsDispatcher = get()
        )
    }

    single<ConnectionStatusResources> { ConnectionStatusResourcesImpl(get()) }
    single { QueryInternetConnection(get()) }
}
