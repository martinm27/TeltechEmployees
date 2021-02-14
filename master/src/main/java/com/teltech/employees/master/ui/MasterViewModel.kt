package com.teltech.employees.master.ui

import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.employeeslib.mapper.toEmployeeName
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import com.teltech.employees.master.ui.mapper.toViewStateModel
import com.teltech.employees.navigation.RoutingActionsDispatcher
import com.teltech.employees.navigation.model.EmployeeParcelable
import io.reactivex.Scheduler

class MasterViewModel(
    queryAllEmployees: QueryAllEmployees,
    mainThreadScheduler: Scheduler,
    backgroundScheduler: Scheduler,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<MasterViewState>(
    mainThreadScheduler,
    backgroundScheduler,
    routingActionsDispatcher
) {

    private var employeeList = mutableListOf<Employee>()

    init {
        query(queryAllEmployees().map(this::toViewState))
    }

    private fun toViewState(employees: List<Employee>): MasterViewState =
        if (employees.isEmpty()) {
            MasterViewState.EmptyViewState
        } else {
            employeeList.clear()
            employeeList.addAll(employees)
            MasterViewState.EmployeesListViewState(employeeList.map(::toViewStateModel))
        }

    fun showEmployeeDetails(index: Int) = dispatchRoutingAction {
        it.showEmployeeDetails(toEmployeeParcelable(employeeList[index]))
    }

    private fun toEmployeeParcelable(employee: Employee): EmployeeParcelable =
        with(employee) {
            EmployeeParcelable(
                imageUrl,
                toEmployeeName(name, surname),
                title,
                intro,
                description,
                department
            )
        }
}

