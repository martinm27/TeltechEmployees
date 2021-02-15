package com.teltech.employees.coreui.di

import android.app.Application
import android.content.res.Resources
import com.teltech.employees.coreui.R
import com.teltech.employees.coreui.utils.CircularProgressDrawableFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun coreUiModule(): Module = module {

    single {
        CircularProgressDrawableFactory(
            androidContext(),
            androidContext().resources.getDimension(R.dimen.coreui_default_spinner_radius)
        )
    }

    single<Resources> { get<Application>().resources }
}
