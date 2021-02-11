package com.teltech.employees.core.extension

import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit
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
    data class ExponentialRetryInfo(val throwable: Throwable, val exponent: Int)

    fun Int.pow(x: Int) = toDouble().pow(x.toDouble()).toLong()

    return retryWhen { throwable ->
        Flowable.zip(
            throwable,
            Flowable.range(0, retryCount + 1), // +1 because we propagate the error on the last one
            BiFunction(::ExponentialRetryInfo)
        )
            .flatMapSingle { info ->
                when {
                    info.exponent == 0 -> Single.timer(initialDelayInMs, TimeUnit.MILLISECONDS).observeOn(
                        scheduler
                    )
                    info.exponent < retryCount -> Single.timer(
                        baseExponentialDelayInSeconds.pow(
                            info.exponent
                        ), TimeUnit.SECONDS
                    ).observeOn(scheduler)
                    else -> Single.error(info.throwable)
                }
            }
    }
}
