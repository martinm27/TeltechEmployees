package com.teltech.employees.core.analytics

import com.teltech.employees.core.analytics.model.EventName
import com.teltech.employees.core.analytics.model.EventParameter

class Analytics {

    companion object {
        private lateinit var analytics: AnalyticsLogging

        fun setUp(analytics: AnalyticsLogging) {
            Companion.analytics = analytics
        }

        fun logEvent(eventName: EventName, vararg parameters: EventParameter) {
            analytics.logEvent(eventName, *parameters)
        }
    }
}
