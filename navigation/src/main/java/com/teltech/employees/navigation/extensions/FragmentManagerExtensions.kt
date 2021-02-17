package com.teltech.employees.navigation.extensions

import android.os.Handler
import android.os.Looper
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/** Must be called from main thread. */
fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
    ensureMainThread { beginTransaction().func().commitNow() }

/** Must be called from main thread. */
fun FragmentManager.inTransactionAndAddToBackStack(
    name: String? = null,
    func: FragmentTransaction.() -> FragmentTransaction
) =
    ensureMainThread {
        beginTransaction().func().addToBackStack(name).commit()
        executePendingTransactions()
    }

private fun ensureMainThread(block: () -> Unit) {
    val mainLooper = Looper.getMainLooper()

    if (mainLooper.thread == Thread.currentThread()) block() else Handler(mainLooper).post { block() }
}
