package com.teltech.employees.navigation

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager

private const val ROUTING_ACTION_THROTTLE_WINDOW = 300

abstract class MainRouter(
    protected val fragmentManager: FragmentManager
) : Router {

    private val mainHandler = Handler(Looper.getMainLooper())

    private var lastActionStamp = 0L

    init {
        fragmentManager.addOnBackStackChangedListener {
            mainHandler.post {
                fragmentManager.peekBackStack()?.let {
                    if (!it.name.isNullOrBlank()) {
                        fragmentManager.popBackStackImmediate()
                    }
                }
            }
        }
    }

    /** Dispatches action on main thread by posting the action. If the current thread is main thread, action is executed immediately. */
    protected fun dispatchOnMainThread(action: () -> Unit) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            action()
        } else {
            mainHandler.post(action::invoke)
        }
    }

    /** Dispatches action on main thread by always posting the action. */
    protected fun dispatchOnMainThreadWithForcedPost(action: () -> Unit) {
        mainHandler.post(action::invoke)
    }

    protected fun dispatchOnMainThreadWithThrottle(action: () -> Unit) {
        dispatchOnMainThread {
            System.currentTimeMillis().let {
                if (lastActionStamp < it - ROUTING_ACTION_THROTTLE_WINDOW) {
                    lastActionStamp = it
                    action()
                }
            }
        }
    }

    protected fun propagateBackToTopFragment(fragmentManager: FragmentManager): Boolean {
        return callIfPresent(
            findBackPropagatingFragment(fragmentManager),
            BackPropagatingFragment::back
        )
    }

    protected fun callIfPresent(
        backPropagatingFragment: BackPropagatingFragment?,
        function: (BackPropagatingFragment) -> Boolean
    ): Boolean {
        return backPropagatingFragment?.let(function::invoke) ?: false
    }

    protected fun findBackPropagatingFragment(fragmentManager: FragmentManager): BackPropagatingFragment? =
        fragmentManager.fragments.reversed()
            .firstOrNull { it is BackPropagatingFragment } as? BackPropagatingFragment
}
