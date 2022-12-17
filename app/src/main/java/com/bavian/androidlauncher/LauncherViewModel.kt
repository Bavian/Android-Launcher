package com.bavian.androidlauncher

import android.app.Activity
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bavian.androidlauncher.apps.AppData
import com.bavian.androidlauncher.apps.AppsCollector
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LauncherViewModel(
    private val packageManager: PackageManager,
    private val appsCollector: AppsCollector
) : ViewModel() {

    private val _appsList = MutableStateFlow(appsCollector.collectApps())
    val appsList = _appsList.asStateFlow()

    fun onStart() {
        refreshApps()
    }

    fun appClicked(activity: Activity, appData: AppData) {
        activity.startActivity(appData.getLaunchIntent())
    }

    private fun refreshApps() {
        viewModelScope.launch { _appsList.emit(appsCollector.collectApps()) }
    }

    private fun AppData.getLaunchIntent() =
        packageManager.getLaunchIntentForPackage(packageName.toString())
}