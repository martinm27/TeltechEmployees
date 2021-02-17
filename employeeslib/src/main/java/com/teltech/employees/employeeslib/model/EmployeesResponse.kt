package com.teltech.employees.employeeslib.model

sealed class EmployeesResponse {
    data class Success(val employeesList: List<Employee>): EmployeesResponse()
    object Failure : EmployeesResponse()
}
