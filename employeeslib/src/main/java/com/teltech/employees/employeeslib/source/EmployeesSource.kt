package com.teltech.employees.employeeslib.source

import com.teltech.employees.employeeslib.model.EmployeesResponse
import io.reactivex.Completable
import io.reactivex.Flowable

interface EmployeesSource {

    fun employees(): Flowable<EmployeesResponse>

    fun refresh(): Completable
}
