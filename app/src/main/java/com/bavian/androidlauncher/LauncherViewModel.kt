package com.bavian.androidlauncher

import android.app.Activity
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import com.bavian.androidlauncher.apps.AppData
import com.bavian.androidlauncher.apps.AppsCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LauncherViewModel(
    private val packageManager: PackageManager,
    appsCollector: AppsCollector
) : ViewModel() {

    private val _appsList = MutableStateFlow(appsCollector.collectApps())
    val appsList = _appsList.asStateFlow()

    fun appClicked(activity: Activity, appData: AppData) {
        activity.startActivity(appData.getLaunchIntent())
    }

    private fun AppData.getLaunchIntent() =
        packageManager.getLaunchIntentForPackage(packageName.toString())
}