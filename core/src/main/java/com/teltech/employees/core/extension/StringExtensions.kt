package com.teltech.employees.core.extension

inline fun String?.ifNullOrEmpty(defaultValue: () -> String): String = if (this == null || isEmpty() || isBlank()) defaultValue() else this
