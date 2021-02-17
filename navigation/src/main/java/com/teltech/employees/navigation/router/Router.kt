package com.teltech.employees.navigation.router

import com.teltech.employees.navigation.model.EmployeeParcelable

interface Router {

    fun finishHostActivity()

    fun goBack()

    fun showEmployees()

    fun showEmployeeDetails(employeeParcelable: EmployeeParcelable)
}
