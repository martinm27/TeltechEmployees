package com.teltech.employees.core.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.teltech.employees.core.analytics.model.EventName
import com.teltech.employees.core.analytics.model.EventParameter
import com.teltech.employees.core.extension.subscribeWithOnError
import io.reactivex.Completable
import io.reactivex.Scheduler
import timber.log.Timber

class AnalyticsLoggingImpl(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val backgroundScheduler: Scheduler
) : AnalyticsLogging {

    override fun logEvent(eventName: EventName, vararg parameters: EventParameter) {
        Completable.create {
            val bundle = if (parameters.isNotEmpty()) {
                Bundle().apply {
                    parameters.forEach {
                        when (it.value) {
                            is String -> putString(it.key.eventId, it.value)
                            is Int -> putInt(it.key.eventId, it.value)
                            is Long -> putLong(it.key.eventId, it.value)
                            is Float -> putFloat(it.key.eventId, it.value)
                            is Double -> putDouble(it.key.eventId, it.value)
                            else -> throw IllegalArgumentException("Unsupported analytics parameter value type.")
                        }
                    }
                }
            } else {
                null
            }

            firebaseAnalytics.logEvent(eventName.eventId, bundle)
        }
            .subscribeOn(backgroundScheduler)
            .subscribeWithOnError(Timber::w)
    }
}
