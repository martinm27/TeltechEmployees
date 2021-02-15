package com.teltech.employees.core.extension

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.math.pow

private const val REF_COUNT_TIMEOUT_SECONDS = 1L

/** Shares the chain and replays the latest emission to new subscribers. Delays the chain dispose for [REF_COUNT_TIMEOUT_SECONDS] */
fun <T> Flowable<T>.shareReplayLatest(): Flowable<T> =
    replay(1).refCount(REF_COUNT_TIMEOUT_SECONDS, TimeUnit.SECONDS)

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
                    val delay = initialDelayInMs * baseExponentialDelayInSeconds.pow(currentStep - 1)
                    Single.timer(delay, TimeUnit.MILLISECONDS, scheduler)
                } else {
                    Single.error(throwable)
                }
            }
        }
}
