package com.teltech.employees.navigation

import com.teltech.employees.navigation.model.EmployeeParcelable

interface Router {

    fun finishHostActivity()

    fun clearAll()

    fun goBack()

    fun showEmployees()

    fun showEmployeeDetails(employeeParcelable: EmployeeParcelable)
}
