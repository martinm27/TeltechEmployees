package com.teltech.employees.master.ui

import android.os.Bundle
import android.view.View
import com.teltech.employees.coreui.BaseFragment
import com.teltech.employees.coreui.utils.addCustomDecoration
import com.teltech.employees.coreui.utils.hide
import com.teltech.employees.coreui.utils.show
import com.teltech.employees.master.R
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
        binding.employeesRecyclerView.apply {
            adapter = masterAdapter
            addCustomDecoration(context, R.drawable.ic_vertical_divider)
        }
        setStatusBarColor(android.R.color.white)
    }

    override fun render(viewState: MasterViewState) {
        when (viewState) {
            is MasterViewState.EmployeesListViewState -> renderEmployees(viewState.employeeList)
            is MasterViewState.EmptyViewState -> renderEmptyState()
        }
    }

    private fun renderEmployees(employees: List<EmployeeViewStateModel>) {
        masterAdapter.submitList(employees)

        with(binding) {
            employeesEmptyState.hide()
            employeesRecyclerView.show()
        }
    }

    private fun renderEmptyState() {
        with(binding) {
            employeesRecyclerView.hide()
            employeesEmptyState.show()
        }
    }
}
