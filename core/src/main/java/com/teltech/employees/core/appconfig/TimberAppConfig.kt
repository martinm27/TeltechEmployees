package com.teltech.employees.core.appconfig

import com.teltech.employees.core.timber.DebugTree
import timber.log.Timber

/**
 * Timber app configuration
 */
class TimberAppConfig : AppConfig {

    override fun configure() = Timber.plant(DebugTree())
}
