package com.teltech.employees.master.ui.mapper

import com.teltech.employees.employeeslib.mapper.toEmployeeName
import com.teltech.employees.employeeslib.model.Employee
import com.teltech.employees.master.ui.EmployeeViewStateModel

fun toViewStateModel(employee: Employee): EmployeeViewStateModel =
    with(employee) {
        EmployeeViewStateModel(
            index,
            toEmployeeName(name, surname),
            title,
            imageUrl
        )
    }



