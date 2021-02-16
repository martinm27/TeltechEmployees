package com.teltech.employees.core.analytics

import com.teltech.employees.core.analytics.model.EventName
import com.teltech.employees.core.analytics.model.EventParameter

class EmptyAnalyticsLogging : AnalyticsLogging {

    override fun logEvent(eventName: EventName, vararg parameters: EventParameter) = Unit
}
