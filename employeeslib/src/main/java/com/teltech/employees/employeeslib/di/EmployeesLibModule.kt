package com.teltech.employees.employeeslib.di

import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import com.teltech.employees.employeeslib.usecase.QueryEmployeeWithId
import org.koin.core.module.Module
import org.koin.dsl.module

fun employeesLibModule(): Module = module {

    single { QueryAllEmployees(get()) }
    single { QueryEmployeeWithId(get()) }
}
