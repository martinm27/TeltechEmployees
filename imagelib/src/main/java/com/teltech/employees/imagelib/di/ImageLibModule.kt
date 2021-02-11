package com.teltech.employees.imagelib.di

import com.teltech.employees.imagelib.imageloader.ImageQueryLoader
import com.teltech.employees.imagelib.imageloader.ImageQueryLoaderImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

fun imageLibModule(): Module = module {

    single<ImageQueryLoader> { ImageQueryLoaderImpl(androidContext(), get()) }
}
