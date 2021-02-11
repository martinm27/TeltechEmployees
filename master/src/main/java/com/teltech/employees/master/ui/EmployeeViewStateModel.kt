package com.teltech.employees.master.ui

import com.teltech.employees.coreui.utils.ListItem

data class EmployeeViewStateModel(
    val index: Int,
    val title: String,
    val subtitle: String,
    val imageUrl: String,
) : ListItem(index)
