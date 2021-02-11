package com.teltech.employees.core.timber

import android.util.Log
import timber.log.Timber

/**
 * Extends [Timber.DebugTree] but:
 * - Tag is a link to the log
 * - Throws exception if priority == [Log.ERROR]
 */
open class DebugTree : Timber.DebugTree() {

    override fun log(priority: Int, tag: String?, message: String, throwable: Throwable?) {
        if (priority == Log.ERROR) {
            throw throwable ?: RuntimeException(message)
        }

        super.log(priority, tag, message, throwable)
    }

    override fun createStackElementTag(element: StackTraceElement) = "(${element.fileName}:${element.lineNumber})#${element.methodName} DebugTree"
}
