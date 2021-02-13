package com.teltech.employees.navigation

interface Router {

    fun finishHostActivity()

    fun clearAll()

    fun goBack()

    fun showEmployees()

    fun showEmployeeDetails(employeeId: Int)
}
