package com.teltech.employees.core.extension

fun String?.foldNullOrBlank(notNullAction: (String) -> Unit, nullAction: () -> Unit) =
        if (this.isNullOrBlank()) {
            nullAction()
        } else {
            notNullAction(this)
        }

inline fun String.ifEmptyOrBlank(defaultValue: () -> String): String = if (isEmpty() || isBlank()) defaultValue() else this

inline fun <T> String.mapIfNotBlank(value: (String) -> T): T? = if (isEmpty() || isBlank()) null else value(this)

fun <T> joinWith(vararg args: T, separator: String = " ", transform: (T) -> String = { it.toString() }): String =
        String.format("%s$separator".repeat(args.size).removeSuffix(separator), *args.map(transform).toTypedArray())

fun <T> Collection<T>.joinWith(separator: String = " ", transform: (T) -> String = { it.toString() }): String =
        String.format("%s$separator".repeat(size).removeSuffix(separator), *map(transform).toTypedArray())
