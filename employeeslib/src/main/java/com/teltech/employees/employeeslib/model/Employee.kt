package com.teltech.employees.employeeslib.model

data class Employee(
    val index: Int,
    val department: String,
    val name: String,
    val surname: String,
    val imageUrl: String,
    val title: String,
    val agency: String,
    val intro: String,
    val description: String
)
