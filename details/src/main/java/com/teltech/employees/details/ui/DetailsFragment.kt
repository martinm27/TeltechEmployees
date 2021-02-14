package com.teltech.employees.details.ui

import android.os.Bundle
import android.view.View
import com.teltech.employees.coreui.BaseFragment
import com.teltech.employees.details.R
import com.teltech.employees.details.databinding.FragmentDetailsBinding
import com.teltech.employees.navigation.model.EmployeeParcelable
import com.teltech.employees.imagelib.imageloader.ImageQueryLoader
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val KEY_EMPLOYEE = "key_employee"

class DetailsFragment :
    BaseFragment<DetailsViewState, FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    companion object {
        const val TAG = "DetailsFragment"

        fun newInstance(employeeParcelable: EmployeeParcelable): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_EMPLOYEE, employeeParcelable)
                }
            }
    }

    override val model: DetailsViewModel by viewModel(
        parameters = { parametersOf(requireArguments().getParcelable(KEY_EMPLOYEE)) }
    )
    private val imageLoader: ImageQueryLoader by inject()

    override fun FragmentDetailsBinding.initialiseView(view: View, savedInstanceState: Bundle?) {
        binding.detailsBackButton.setOnClickListener { model.back() }
        setStatusBarColor(android.R.color.transparent)
    }

    override fun render(viewState: DetailsViewState) {
        with(viewState.detailsViewStateModel) {
            imageLoader.loadWithKey(
                binding.employeeImage,
                imageUrl,
                R.drawable.ic_placeholder
            )
            binding.employeeName.text = name
            binding.employeeRole.text = role
            binding.employeeIntro.text = intro
            binding.employeeDescription.text = description
            binding.employeeDepartment.text = department
        }
    }
}
