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

fun <T> Flowable<T>.exponentialRetry(
    retryCount: Int = 6,
    initialDelayInMs: Long = 300,
    baseExponentialDelayInSeconds: Int = 2,
    scheduler: Scheduler
): Flowable<T> {
    fun Int.pow(x: Int) = toDouble().pow(x.toDouble()).toLong()

    val retryStep = AtomicInteger(0)

    return this.doOnNext { retryStep.set(0) }
        .retryWhen { throwableFlowable ->
            throwableFlowable.switchMapSingle { throwable ->
                val currentStep = retryStep.incrementAndGet()
                if (currentStep <= retryCount) {
                    val delay =
                        initialDelayInMs * baseExponentialDelayInSeconds.pow(currentStep - 1)
                    Single.timer(delay, TimeUnit.MILLISECONDS, scheduler)
                } else {
                    Single.error(throwable)
                }
            }
        }
}
