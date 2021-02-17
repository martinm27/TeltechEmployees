package com.teltech.employees.master.di

import android.view.LayoutInflater
import com.teltech.employees.core.di.BACKGROUND_SCHEDULER
import com.teltech.employees.core.di.MAIN_SCHEDULER
import com.teltech.employees.master.ui.MasterViewModel
import com.teltech.employees.master.ui.MasterViewState
import com.teltech.employees.master.ui.adapter.MasterAdapter
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun masterModule(): Module = module {


    viewModel {
        MasterViewModel(
            get(),
            get(),
            mainThreadScheduler = get(named(MAIN_SCHEDULER)),
            backgroundScheduler = get(named(BACKGROUND_SCHEDULER)),
            routingActionsMediator = get()
        )
    }

    factory {
        val layoutInflater: LayoutInflater = it[0]
        val onClickListener: (Int) -> Unit = it[1]
        MasterAdapter(layoutInflater, get(), onClickListener)
    }
}
