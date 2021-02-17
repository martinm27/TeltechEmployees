package com.teltech.employees.details.ui

import com.teltech.employees.core.constants.UNKNOWN
import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.navigation.model.EmployeeParcelable
import com.teltech.employees.navigation.routingmediator.RoutingMediator
import io.reactivex.Flowable
import io.reactivex.Scheduler

/**
 * This class is responsible for initial view state publishing but no other logic is
 * present here because we cannot uniquely identify employees at the moment.
 */
class DetailsViewModel(
    employeeParcelable: EmployeeParcelable,
    mainThreadScheduler: Scheduler,
    backgroundScheduler: Scheduler,
    routingActionsMediator: RoutingMediator
) : BaseViewModel<DetailsViewState>(
    mainThreadScheduler,
    backgroundScheduler,
    routingActionsMediator
) {
    init {
        query(Flowable.just(DetailsViewState(toViewStateModel(employeeParcelable))))
    }

    private fun toViewStateModel(employee: EmployeeParcelable): DetailsViewStateModel =
        with(employee) {
            DetailsViewStateModel(
                imageUrl ?: UNKNOWN,
                name ?: UNKNOWN,
                role ?: UNKNOWN,
                intro ?: UNKNOWN,
                description ?: UNKNOWN,
                department ?: UNKNOWN
            )
        }
}
