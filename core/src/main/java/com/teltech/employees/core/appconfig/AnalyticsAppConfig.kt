package com.teltech.employees.core.appconfig

import android.content.Context
import com.google.firebase.analytics.FirebaseAnalytics
import com.teltech.employees.core.BuildConfig
import com.teltech.employees.core.analytics.Analytics
import com.teltech.employees.core.analytics.AnalyticsLoggingImpl
import com.teltech.employees.core.analytics.EmptyAnalyticsLogging
import io.reactivex.Scheduler

class AnalyticsAppConfig(private val context: Context, private val backgroundScheduler: Scheduler) :
    AppConfig {
    override fun configure() {
        FirebaseAnalytics.getInstance(context).let {
            when (BuildConfig.BUILD_TYPE) {
                "debug" -> Analytics.setUp(EmptyAnalyticsLogging())
                "release" -> Analytics.setUp(AnalyticsLoggingImpl(it, backgroundScheduler))
                else -> throw IllegalStateException("Analytics configuration not set for ${BuildConfig.BUILD_TYPE} build type")
            }
        }
    }
}
