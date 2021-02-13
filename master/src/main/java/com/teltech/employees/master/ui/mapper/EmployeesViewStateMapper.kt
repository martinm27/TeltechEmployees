package com.teltech.employees.master.ui.mapper

import com.teltech.employees.core.constants.SPACING_SEPARATOR
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.master.ui.EmployeeViewStateModel

fun toViewStateModel(employee: Employee): EmployeeViewStateModel =
    with(employee) {
        EmployeeViewStateModel(
            index,
            buildViewStateTitle(name, surname),
            title,
            imageUrl
        )
    }

private fun buildViewStateTitle(name: String, surname: String) =
    StringBuilder().append(name).append(SPACING_SEPARATOR).append(surname).toString()



