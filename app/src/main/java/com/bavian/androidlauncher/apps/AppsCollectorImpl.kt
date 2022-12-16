package com.bavian.androidlauncher.apps

import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

class AppsCollectorImpl(private val packageManager: PackageManager) : AppsCollector {

    private val launcherIntent
        get() = Intent(Intent.ACTION_MAIN, null).apply { addCategory(Intent.CATEGORY_LAUNCHER) }

    override fun collectApps(): List<AppData> = packageManager
        .queryIntentActivitiesCompat(launcherIntent)
        .map(::toAppData)

    private fun toAppData(resolveInfo: ResolveInfo) = AppData(
        resolveInfo.loadLabel(packageManager),
        resolveInfo.activityInfo.packageName,
        resolveInfo.loadIcon(packageManager),
    )
}

@Suppress("DEPRECATION")
private fun PackageManager.queryIntentActivitiesCompat(intent: Intent): List<ResolveInfo> {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        queryIntentActivities(intent, 0)
    } else {
        queryIntentActivities(intent, PackageManager.ResolveInfoFlags.of(0))
    }
}
