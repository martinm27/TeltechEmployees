package com.teltech.employees.core.usecase

import io.reactivex.Completable

interface CommandUseCaseWithParam<Param> {

    operator fun invoke(param: Param): Completable
}
