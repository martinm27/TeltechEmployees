package com.teltech.employees.navigation

import org.koin.core.module.Module
import org.koin.dsl.module

fun navigationModule(): Module = module {

    single<RoutingMediator> { RoutingMediatorImpl() }

    single<RoutingActionsDispatcher> { get<RoutingMediator>() }
    single<RoutingActionsSource> { get<RoutingMediator>() }
}
