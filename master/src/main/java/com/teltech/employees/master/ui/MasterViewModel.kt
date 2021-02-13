package com.teltech.employees.master.ui

import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import com.teltech.employees.master.ui.mapper.toViewStateModel
import com.teltech.employees.navigation.RoutingActionsDispatcher
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

    init {
        query(queryAllEmployees().map(this::toViewState))
    }

    private fun toViewState(employees: List<Employee>): MasterViewState =
        if (employees.isEmpty()) {
            MasterViewState.EmptyViewState
        } else {
            MasterViewState.EmployeesListViewState(employees.map(::toViewStateModel))
        }

    fun showEmployeeDetails(index: Int) = dispatchRoutingAction {
        it.showEmployeeDetails(index)
    }
}

