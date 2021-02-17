package com.teltech.employees.navigation.di

import com.teltech.employees.navigation.routingmediator.RoutingMediator
import com.teltech.employees.navigation.routingmediator.RoutingMediatorImpl
import org.koin.core.module.Module
import org.koin.dsl.module

fun navigationModule(): Module = module {

    single<RoutingMediator> { RoutingMediatorImpl() }
}
