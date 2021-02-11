package com.teltech.employees.core.extension

fun <T> Collection<T>.hasSingleElement() = size == 1

/**
 * Since native [Collection.all] extension function returns [true] if the collection is empty,
 * this extension function is checking the collection size and predicate satisfaction on each collection element.
 */
fun <T> Collection<T>.hasItemsAndAll(predicate: (T) -> Boolean): Boolean =
    isNotEmpty() && all(predicate::invoke)

/**
 * This extension function is checking if the size is empty or any of the predicate is true
 */
fun <T> Collection<T>.hasNothingOrAny(predicate: (T) -> Boolean): Boolean =
    isEmpty() || any(predicate::invoke)

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> =
    map { if (block(it)) newValue else it }
