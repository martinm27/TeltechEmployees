package com.teltech.employees.navigation

import com.teltech.employees.navigation.router.Router

interface RoutingActionConsumer {

    fun onRoutingAction(routingAction: (Router) -> Unit)
}
