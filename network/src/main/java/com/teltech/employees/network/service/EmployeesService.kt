package com.teltech.employees.network.service

import com.teltech.employees.network.model.APIEmployee
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET

interface EmployeesService {

    @GET("/teltechiansFlat.json")
    fun getEmployees(): Single<List<APIEmployee?>>
}
