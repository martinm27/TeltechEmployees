package com.teltech.employees.details.ui

import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.employeeslib.mapper.toEmployeeName
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.employeeslib.usecase.QueryEmployeeWithId
import com.teltech.employees.navigation.RoutingActionsDispatcher
import io.reactivex.Scheduler

class DetailsViewModel(
    employeeId: Int,
    queryEmployeeWithId: QueryEmployeeWithId,
    mainThreadScheduler: Scheduler,
    backgroundScheduler: Scheduler,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<DetailsViewState>(
    mainThreadScheduler,
    backgroundScheduler,
    routingActionsDispatcher
) {

    init {
        query(queryEmployeeWithId(employeeId).map(this::toViewState))
    }

    private fun toViewState(employee: Employee?): DetailsViewState =
        if (employee == null) {
            DetailsViewState.Empty
        } else {
            DetailsViewState.Employee(toViewStateModel(employee))
        }

    private fun toViewStateModel(employee: Employee): DetailsViewStateModel =
        with(employee) {
            DetailsViewStateModel(
                imageUrl,
                toEmployeeName(name, surname),
                title,
                intro,
                description,
                department
            )
        }
}
