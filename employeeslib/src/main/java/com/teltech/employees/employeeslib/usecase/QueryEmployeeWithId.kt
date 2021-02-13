package com.teltech.employees.employeeslib.usecase

import com.teltech.employees.core.usecase.QueryUseCaseWithParam
import com.teltech.employees.employeeslib.model.Employee
import io.reactivex.Flowable

class QueryEmployeeWithId(private val queryAllEmployees: QueryAllEmployees) :
    QueryUseCaseWithParam<Int, Employee?> {

    override fun invoke(param: Int): Flowable<Employee?> = queryAllEmployees()
        .map { employees -> employees.find { it.index == param } }
}
