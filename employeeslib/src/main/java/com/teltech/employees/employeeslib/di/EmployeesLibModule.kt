package com.teltech.employees.employeeslib.di

import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import org.koin.core.module.Module
import org.koin.dsl.module

fun employeesLibModule(): Module = module {

    single { QueryAllEmployees(get()) }
}
