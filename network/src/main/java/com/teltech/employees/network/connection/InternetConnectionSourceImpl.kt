package com.teltech.employees.network.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.processors.BehaviorProcessor

class InternetConnectionSourceImpl(
    private val context: Context,
    backgroundScheduler: Scheduler
) : InternetConnectionSource {

    private val internetConnectionBehaviorProcessor: BehaviorProcessor<Boolean> =
        BehaviorProcessor.createDefault(false)

    private val internetConnectionStatusFlowable: Flowable<Boolean> =
        internetConnectionBehaviorProcessor.distinctUntilChanged()
            .observeOn(backgroundScheduler)

    init {
        startNetworkCallback()
    }

    private fun startNetworkCallback() {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder: NetworkRequest.Builder = NetworkRequest.Builder()

        connectivityManager.registerNetworkCallback(builder.build(),
            object : ConnectivityManager.NetworkCallback() {

                override fun onAvailable(network: Network) =
                    internetConnectionBehaviorProcessor.onNext(true)

                override fun onLost(network: Network) =
                    internetConnectionBehaviorProcessor.onNext(false)

                override fun onUnavailable() {
                    internetConnectionBehaviorProcessor.onNext(false)
                }
            })
    }

    override fun isConnectedToInternet(): Flowable<Boolean> = internetConnectionStatusFlowable
}
