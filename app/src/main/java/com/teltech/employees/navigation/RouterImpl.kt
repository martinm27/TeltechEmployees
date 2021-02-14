package com.teltech.employees.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.teltech.employees.R
import com.teltech.employees.details.ui.DetailsFragment
import com.teltech.employees.master.ui.MasterFragment
import com.teltech.employees.navigation.model.EmployeeParcelable

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_FLOW_CONTAINER = R.id.activity_main_container

class RouterImpl(
    private val activity: AppCompatActivity,
    fragmentManager: FragmentManager,
) : MainRouter(fragmentManager), Router {

    override fun showEmployees(): Unit = fragmentManager.inTransaction {
        add(MAIN_FLOW_CONTAINER, MasterFragment(), MasterFragment.TAG)
    }

    override fun showEmployeeDetails(employeeParcelable: EmployeeParcelable) =
        fragmentManager.inTransactionAndAddToBackStack {
            add(
                MAIN_FLOW_CONTAINER,
                DetailsFragment.newInstance(employeeParcelable),
                DetailsFragment.TAG
            )
        }

    override fun clearAll() = fragmentManager.safeClearBackStack()

    override fun goBack() = dispatchOnMainThreadWithThrottle(this::goBackInternal)

    private fun goBackInternal() {
        if (fragmentManager.backStackEntryCount != LAST_FRAGMENT) {
            fragmentManager.popBackStackImmediate()
        } else {
            finishHostActivity()
        }
    }

    override fun finishHostActivity() = activity.finish()
}
