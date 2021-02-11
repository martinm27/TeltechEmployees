package com.teltech.employees.employeeslib.usecase

import com.teltech.employees.core.extension.shareReplayLatest
import com.teltech.employees.core.mock.Mockable
import com.teltech.employees.core.usecase.QueryUseCase
import com.teltech.employees.employeeslib.mapper.toEmployees
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.network.service.EmployeesService
import io.reactivex.Flowable

@Mockable
class QueryAllEmployees(
    private val employeesService: EmployeesService
) : QueryUseCase<List<Employee>> {

    override fun invoke(): Flowable<List<Employee>> =
        employeesService.getEmployees()
            .toFlowable()
            .distinctUntilChanged()
            .map(::toEmployees)
            .shareReplayLatest()
}
