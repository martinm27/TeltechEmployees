package com.teltech.network.service

import com.teltech.network.model.APIEmployee
import io.reactivex.Single
import retrofit2.http.GET

interface EmployeesService {

    @GET("/teltechiansFlat.json")
    fun getEmployees(): Single<List<APIEmployee>>
}
