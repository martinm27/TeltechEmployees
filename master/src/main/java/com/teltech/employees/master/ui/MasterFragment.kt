package com.teltech.employees.master.ui

import android.os.Bundle
import android.view.View
import com.teltech.employees.coreui.BaseFragment
import com.teltech.employees.master.databinding.FragmentMasterBinding
import com.teltech.employees.master.ui.adapter.MasterAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MasterFragment :
    BaseFragment<MasterViewState, FragmentMasterBinding>(FragmentMasterBinding::inflate) {

    companion object {
        const val TAG = "MasterFragment"
    }

    override val model: MasterViewModel by viewModel()

    private val masterAdapter: MasterAdapter by inject(
        parameters = {
            parametersOf(
                layoutInflater,
                model::showEmployeeDetails
            )
        }
    )

    override fun FragmentMasterBinding.initialiseView(view: View, savedInstanceState: Bundle?) {
        // Add RecyclerView to adapter
    }

    override fun render(viewState: MasterViewState) {
        when (viewState) {
            is MasterViewState.EmployeesListViewState -> renderEmployees(viewState.employeeList)
            is MasterViewState.EmptyViewState -> renderEmptyState()
        }
    }

    private fun renderEmployees(employees: List<EmployeeViewStateModel>) {
        masterAdapter.submitList(employees)
    }

    private fun renderEmptyState() {

    }
}
