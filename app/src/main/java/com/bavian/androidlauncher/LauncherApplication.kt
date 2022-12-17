package com.bavian.androidlauncher

import android.app.Application
import android.content.pm.PackageManager
import com.bavian.androidlauncher.apps.AppsCollector
import com.bavian.androidlauncher.apps.AppsCollectorImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class LauncherApplication : Application() {

    private val appModule = module {
        single<PackageManager> { packageManager }
        single<AppsCollector> { AppsCollectorImpl(get()) }
        viewModelOf(::LauncherViewModel)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@LauncherApplication)
            modules(appModule)
        }
    }
}