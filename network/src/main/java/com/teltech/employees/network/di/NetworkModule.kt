package com.teltech.employees.network.di

import com.teltech.employees.network.service.EmployeesService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_URL = "https://teltech.co/"

fun networkModule(): Module = module {

    single<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            connectTimeout(100, TimeUnit.SECONDS)
            readTimeout(100, TimeUnit.SECONDS)
            addInterceptor(get())
        }
            .build()
    }

    single<EmployeesService> {
        Retrofit.Builder()
            .baseUrl(API_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(EmployeesService::class.java)
    }
}
