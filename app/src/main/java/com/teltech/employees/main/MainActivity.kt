package com.teltech.employees.main

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.teltech.employees.R
import com.teltech.employees.navigation.router.Router
import com.teltech.employees.navigation.RoutingActionConsumer
import com.teltech.employees.navigation.routingmediator.RoutingMediator
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(R.layout.activity_main), RoutingActionConsumer {

    private val routingMediator: RoutingMediator by inject()
    private val router: Router by inject(parameters = { parametersOf(this) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            router.showEmployees()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        routingMediator.setActiveRoutingActionConsumer(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        routingMediator.unsetRoutingActionConsumer(this)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        if (shouldUnsetRoutingActionConsumer()) {
            routingMediator.unsetRoutingActionConsumer(this)
        }
        super.onPause()
    }

    private fun shouldUnsetRoutingActionConsumer() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) isInMultiWindowMode.not() else true

    override fun onRoutingAction(routingAction: (Router) -> Unit) = routingAction(router)

    override fun onBackPressed() = router.goBack()

}

