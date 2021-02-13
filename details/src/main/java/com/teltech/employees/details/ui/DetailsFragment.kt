package com.teltech.employees.details.ui

import android.os.Bundle
import android.view.View
import com.teltech.employees.coreui.BaseFragment
import com.teltech.employees.details.R
import com.teltech.employees.details.databinding.FragmentDetailsBinding
import com.teltech.employees.imagelib.imageloader.ImageQueryLoader
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val KEY_EMPLOYEE_ID = "key_employee_id"

class DetailsFragment :
    BaseFragment<DetailsViewState, FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    companion object {
        const val TAG = "DetailsFragment"

        fun newInstance(employeeId: Int): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(KEY_EMPLOYEE_ID, employeeId)
                }
            }
    }

    override val model: DetailsViewModel by viewModel(
        parameters = { parametersOf(requireArguments().getInt(KEY_EMPLOYEE_ID)) }
    )
    private val imageLoader: ImageQueryLoader by inject()

    override fun FragmentDetailsBinding.initialiseView(view: View, savedInstanceState: Bundle?) {
        setStatusBarColor(android.R.color.transparent)
    }

    override fun render(viewState: DetailsViewState) {
        when (viewState) {
            is DetailsViewState.Employee -> renderDetails(viewState.detailsViewStateModel)
            is DetailsViewState.Empty -> renderEmptyState()
        }
    }

    private fun renderEmptyState() {
        // TODO: Add error state
    }

    private fun renderDetails(detailsViewStateModel: DetailsViewStateModel) {
        with(detailsViewStateModel) {
            imageLoader.loadWithKey(binding.employeeImage, imageUrl, R.drawable.ic_placeholder)
            binding.employeeName.text = name
            binding.employeeRole.text = role
            binding.employeeIntro.text = intro
            binding.employeeDescription.text = description
            binding.employeeDepartment.text = department
        }
    }
}
