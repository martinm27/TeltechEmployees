package com.teltech.employees.core.appconfig

import io.reactivex.plugins.RxJavaPlugins
import timber.log.Timber

/**
 * RxJava app configuration
 */
class RxJavaErrorHandlingAppConfig : AppConfig {
    override fun configure() {
        RxJavaPlugins.setErrorHandler(Timber::e)
    }
}
