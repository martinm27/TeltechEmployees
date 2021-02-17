package com.teltech.employees.employeeslib.source

import com.teltech.employees.employeeslib.mapper.toEmployees
import com.teltech.employees.employeeslib.model.EmployeesResponse
import com.teltech.employees.network.service.EmployeesService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.processors.BehaviorProcessor

class EmployeesSourceImpl(
    private val employeesService: EmployeesService
) : EmployeesSource {

    private val refreshPublisher = BehaviorProcessor.createDefault(Unit)

    override fun employees(): Flowable<EmployeesResponse> =
        refreshPublisher.switchMap {
            employeesService.getEmployees()
                .map(::toEmployees)
                .onErrorResumeNext(Single.just(EmployeesResponse.Failure))
                .toFlowable()
        }

    override fun refresh(): Completable = Completable.fromAction {
        refreshPublisher.onNext(Unit)
    }
}
