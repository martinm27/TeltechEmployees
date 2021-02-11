package com.teltech.employees.network.model

import com.google.gson.annotations.SerializedName

data class APIEmployee(
    @SerializedName("department")
    val department: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("agency")
    val agency: String?,
    @SerializedName("intro")
    val intro: String?,
    @SerializedName("description")
    val description: String?
)
