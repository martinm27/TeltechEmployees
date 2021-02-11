package com.teltech.employees.navigation

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.teltech.employees.R

private const val LAST_FRAGMENT = 0

@IdRes
private const val MAIN_FLOW_CONTAINER = R.id.activity_main_container

class RouterImpl(
    private val activity: AppCompatActivity,
    fragmentManager: FragmentManager,
) : MainRouter(fragmentManager), Router {

    override fun showEmployees(): Unit = TODO("Not yet implemented")

    override fun showEmployeeDetails(name: String): Unit = TODO("Not yet implemented")

    override fun clearAll() = fragmentManager.safeClearBackStack()

    override fun goBack() = dispatchOnMainThreadWithThrottle(this::goBackInternal)

    private fun goBackInternal() {
        if (!propagateBackToTopFragment(fragmentManager)) {
            if (fragmentManager.backStackEntryCount != LAST_FRAGMENT) {
                fragmentManager.popBackStackImmediate()
            } else {
                finishHostActivity()
            }
        }
    }

    override fun finishHostActivity() = activity.finish()
}
