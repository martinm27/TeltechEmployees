package com.teltech.employees.employeeslib.usecase

import com.teltech.employees.core.mock.Mockable
import com.teltech.employees.core.usecase.QueryUseCase
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.source.EmployeesSource
import io.reactivex.Flowable

@Mockable
class QueryAllEmployees(private val employeesSource: EmployeesSource) :
    QueryUseCase<List<Employee>> {

    override fun invoke(): Flowable<List<Employee>> = employeesSource.employees()
}
