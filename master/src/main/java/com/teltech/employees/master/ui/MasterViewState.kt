package com.teltech.employees.master.ui

sealed class MasterViewState {
    data class EmployeesListViewState(val employeeList: List<EmployeeViewStateModel>) : MasterViewState()
    object EmptyViewState : MasterViewState()
}
