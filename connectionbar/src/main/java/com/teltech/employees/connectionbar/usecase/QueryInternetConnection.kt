package com.teltech.employees.connectionbar.usecase

import com.teltech.employees.core.usecase.QueryUseCase
import com.teltech.employees.network.connection.InternetConnectionSource
import io.reactivex.Flowable

class QueryInternetConnection(private val internetConnectionSource: InternetConnectionSource) :
    QueryUseCase<Boolean> {

    override fun invoke(): Flowable<Boolean> = internetConnectionSource.isConnectedToInternet()

}
