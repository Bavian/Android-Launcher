package com.bavian.apps_collector

import org.koin.dsl.module

val appsDataModule = module {
    single<AppsCollector> { AppsCollectorImpl(get()) }
}
