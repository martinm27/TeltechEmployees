package com.teltech.employees.core.analytics

import com.teltech.employees.core.analytics.model.EventName
import com.teltech.employees.core.analytics.model.EventParameter

interface AnalyticsLogging {

    /**
     * Allows you to log events.
     *
     * @param eventName EventName enum of the event to log
     * @param parameters Input variable number of String parameters
     */
    fun logEvent(eventName: EventName, vararg parameters: EventParameter)
}
