package com.teltech.employees.navigation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployeeParcelable(
    val imageUrl: String?,
    val name: String?,
    val role: String?,
    val intro: String?,
    val description: String?,
    val department: String?
) : Parcelable
