package com.teltech.employees.employeeslib.di

import com.teltech.employees.employeeslib.source.EmployeesSource
import com.teltech.employees.employeeslib.source.EmployeesSourceImpl
import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import com.teltech.employees.employeeslib.usecase.RefreshEmployees
import org.koin.core.module.Module
import org.koin.dsl.module

fun employeesLibModule(): Module = module {

    single<EmployeesSource> { EmployeesSourceImpl(get()) }
    single { QueryAllEmployees(get()) }
    single { RefreshEmployees(get()) }
}
