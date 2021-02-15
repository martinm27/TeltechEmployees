package com.teltech.employees.employeeslib.source

import com.teltech.employees.employeeslib.model.Employee
import io.reactivex.Completable
import io.reactivex.Flowable

interface EmployeesSource {

    fun employees(): Flowable<List<Employee>>

    fun refresh(): Completable
}
