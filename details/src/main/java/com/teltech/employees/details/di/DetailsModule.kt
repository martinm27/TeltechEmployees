package com.teltech.employees.details.di

import com.teltech.employees.core.di.BACKGROUND_SCHEDULER
import com.teltech.employees.core.di.MAIN_SCHEDULER
import com.teltech.employees.details.ui.DetailsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun detailsModule(): Module = module {

    viewModel {
        val employeeId: Int = it[0]
        DetailsViewModel(
            employeeId,
            get(),
            mainThreadScheduler = get(named(MAIN_SCHEDULER)),
            backgroundScheduler = get(named(BACKGROUND_SCHEDULER)),
            routingActionsDispatcher = get()
        )
    }
}
