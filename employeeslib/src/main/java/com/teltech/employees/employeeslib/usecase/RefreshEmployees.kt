package com.teltech.employees.employeeslib.usecase

import com.teltech.employees.core.usecase.CommandUseCase
import com.teltech.employees.employeeslib.source.EmployeesSource
import io.reactivex.Completable

class RefreshEmployees(private val employeesSource: EmployeesSource): CommandUseCase {

    override fun invoke(): Completable = employeesSource.refresh()
}
