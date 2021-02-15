package com.teltech.employees.employeeslib.source

import com.teltech.employees.employeeslib.mapper.toEmployees
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.network.service.EmployeesService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.processors.PublishProcessor

class EmployeesSourceImpl(
    private val employeesService: EmployeesService,
    private val backgroundScheduler: Scheduler
) : EmployeesSource {

    private val refreshPublisher = PublishProcessor.create<Unit>()

    override fun employees(): Flowable<List<Employee>> = employeesService.getEmployees()
        .toFlowable()
        .repeatWhen { refreshPublisher }
        .map(::toEmployees)
        .observeOn(backgroundScheduler)

    override fun refresh(): Completable = Completable.fromAction {
        refreshPublisher.onNext(Unit)
    }
}
