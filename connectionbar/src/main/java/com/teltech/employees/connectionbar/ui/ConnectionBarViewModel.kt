package com.teltech.employees.connectionbar.ui

import com.teltech.employees.connectionbar.resources.ConnectionStatusResources
import com.teltech.employees.connectionbar.ui.ConnectionBarStatus.*
import com.teltech.employees.connectionbar.ui.ConnectionStatusViewStateModel.*
import com.teltech.employees.connectionbar.usecase.QueryInternetConnection
import com.teltech.employees.coreui.BaseViewModel
import com.teltech.employees.navigation.RoutingActionsDispatcher
import io.reactivex.Flowable
import io.reactivex.Scheduler
import java.util.concurrent.TimeUnit

private const val CONNECTED_STATUS_SHOW_DURATION = 3000L

class ConnectionBarViewModel(
    private val connectionStatusResources: ConnectionStatusResources,
    queryInternetConnection: QueryInternetConnection,
    mainThreadScheduler: Scheduler,
    private val backgroundScheduler: Scheduler,
    routingActionsDispatcher: RoutingActionsDispatcher
) : BaseViewModel<ConnectionBarViewState>(
    mainThreadScheduler,
    backgroundScheduler,
    routingActionsDispatcher
) {

    private val connectedStatus: ConnectedStatusViewStateModel by lazy {
        ConnectedStatusViewStateModel(
            connectionStatusResources.connectedLabelText(),
            connectionStatusResources.connectedLabelBackgroundColorRes(),
            connectionStatusResources.connectedDrawableRes()
        )
    }

    private val disconnectedStatus by lazy {
        DisconnectedStatusViewStateModel(
            connectionStatusResources.disconnectedLabelText(),
            connectionStatusResources.disconnectedLabelBackgroundColorRes()
        )
    }

    init {
        query(
            queryInternetConnection().scan(INITIAL) { currentConnectionState, isInternetConnectionReady ->
                determineConnectionStatus(isInternetConnectionReady, currentConnectionState)
            }
                .switchMap { if (it == CONNECTING) startTimer(it) else Flowable.just(it) }
                .map(this::toViewStateAction)
        )
    }

    private fun determineConnectionStatus(
        isInternetConnectionReady: Boolean,
        currentConnectionState: ConnectionBarStatus
    ): ConnectionBarStatus =
        if (!isInternetConnectionReady) {
            DISCONNECTED
        } else {
            when (currentConnectionState) {
                INITIAL -> INITIAL
                DISCONNECTED -> CONNECTING
                else -> CONNECTED
            }
        }

    private fun startTimer(connectingStatus: ConnectionBarStatus): Flowable<ConnectionBarStatus> =
        Flowable.timer(CONNECTED_STATUS_SHOW_DURATION, TimeUnit.MILLISECONDS, backgroundScheduler)
            .map { CONNECTED }
            .startWith(connectingStatus)

    private fun toViewStateAction(newConnectionStatus: ConnectionBarStatus) =
        ConnectionBarViewState(
            when (newConnectionStatus) {
                CONNECTING -> connectedStatus
                DISCONNECTED -> disconnectedStatus
                else -> EmptyConnectionStatusViewStateModel
            }
        )
}


