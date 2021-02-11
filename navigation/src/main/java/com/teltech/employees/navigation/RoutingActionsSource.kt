package com.teltech.employees.navigation

interface RoutingActionsSource {

    fun setActiveRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)

    fun unsetRoutingActionConsumer(routingActionConsumer: RoutingActionConsumer)
}
