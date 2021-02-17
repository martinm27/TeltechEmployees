package com.teltech.employees.core.extension

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

/** Subscribes an empty subscriber with an onError action */
inline fun Completable.subscribeWithOnError(crossinline onError: (Throwable) -> Unit): Disposable = subscribe({}) { onError(it) }
