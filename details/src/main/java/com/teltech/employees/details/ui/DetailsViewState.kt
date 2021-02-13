package com.teltech.employees.details.ui

sealed class DetailsViewState {
    data class Employee(val detailsViewStateModel: DetailsViewStateModel) : DetailsViewState()
    object Empty : DetailsViewState()
}
