package com.teltech.employees.core.appconfig

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.teltech.employees.core.BuildConfig

/**
 * Crashlytics app configuration
 */
class CrashlyticsAppConfig : AppConfig {
    override fun configure() {
        when (BuildConfig.BUILD_TYPE) {
            "debug" -> Unit
            "release" -> FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
            else -> throw IllegalStateException("Crashlytics configuration not set for ${BuildConfig.BUILD_TYPE} build type")
        }
    }
}
