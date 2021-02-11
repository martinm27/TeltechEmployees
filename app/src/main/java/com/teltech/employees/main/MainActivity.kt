package com.teltech.employees.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.teltech.employees.R
import com.teltech.network.service.EmployeesService
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val service: EmployeesService by inject()

    private var disposable: Disposable = Disposables.disposed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            disposable = service.getEmployees()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    Log.d("Employees", "$it")
                }, {
                    it.printStackTrace()
                })
        }
    }

}

