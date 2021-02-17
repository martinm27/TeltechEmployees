package com.teltech.employees.navigation.routingmediator

import com.teltech.employees.navigation.RoutingActionConsumer
import com.teltech.employees.navigation.router.Router

interface RoutingMediator {

    fun setActiveRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)

    fun unsetRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)

    fun dispatch(routingAction: (Router) -> Unit)

    fun dispatchDistinct(actionId: String?, routingAction: (Router) -> Unit)
}
