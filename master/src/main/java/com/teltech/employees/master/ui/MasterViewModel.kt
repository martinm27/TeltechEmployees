package com.teltech.employees.master.ui

import com.teltech.employees.core.analytics.Analytics
import com.teltech.employees.core.analytics.model.EventName
import com.teltech.employees.core.analytics.model.EventParameter
import com.teltech.employees.core.analytics.model.EventParameterName
import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.employeeslib.mapper.toEmployeeName
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.model.EmployeesResponse
import com.teltech.employees.employeeslib.usecase.QueryAllEmployees
import com.teltech.employees.employeeslib.usecase.RefreshEmployees
import com.teltech.employees.master.ui.mapper.toViewStateModel
import com.teltech.employees.navigation.model.EmployeeParcelable
import com.teltech.employees.navigation.routingmediator.RoutingMediator
import io.reactivex.Scheduler

class MasterViewModel(
    private val refreshEmployees: RefreshEmployees,
    queryAllEmployees: QueryAllEmployees,
    mainThreadScheduler: Scheduler,
    backgroundScheduler: Scheduler,
    routingActionsMediator: RoutingMediator
) : BaseViewModel<MasterViewState>(
    mainThreadScheduler,
    backgroundScheduler,
    routingActionsMediator
) {

    private var employeeList = mutableListOf<Employee>()

    init {
        query(queryAllEmployees().map(this::toViewState))
    }

    private fun toViewState(employeesResponse: EmployeesResponse): MasterViewState =
        when (employeesResponse) {
            is EmployeesResponse.Failure -> MasterViewState.ErrorViewState
            is EmployeesResponse.Success ->
                if (employeesResponse.employeesList.isEmpty()) {
                    MasterViewState.EmptyViewState
                } else {
                    employeeList.clear()
                    employeeList.addAll(employeesResponse.employeesList)
                    MasterViewState.EmployeesListViewState(employeeList.map(::toViewStateModel))
                }
        }

    fun refreshEmployees() {
        runCommand(refreshEmployees.invoke())
    }

    fun showEmployeeDetails(index: Int) = dispatchRoutingAction {
        it.showEmployeeDetails(toEmployeeParcelable(employeeList[index]))
        Analytics.logEvent(
            EventName.EMPLOYEE_MASTER_SCREEN,
            EventParameter(EventParameterName.EMPLOYEE_ID, index)
        )
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

