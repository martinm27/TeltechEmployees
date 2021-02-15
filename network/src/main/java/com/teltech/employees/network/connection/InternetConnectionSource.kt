package com.teltech.employees.network.connection

import io.reactivex.Flowable

interface InternetConnectionSource {

    fun isConnectedToInternet(): Flowable<Boolean>
}
