package com.bavian.apps_collector

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Build

class AppsCollectorImpl(private val packageManager: PackageManager) : AppsCollector {

    private val launcherIntent
        get() = Intent(Intent.ACTION_MAIN, null).apply { addCategory(Intent.CATEGORY_LAUNCHER) }

    override fun collectGames() = collectAllApps(packageManager::isGame)

    override fun collectApps() = collectAllApps(packageManager::isNotGame)

    private fun collectAllApps(filter: (ResolveInfo) -> Boolean): List<AppData> = packageManager
        .queryIntentActivitiesCompat(launcherIntent)
        .asSequence()
        .filter(filter)
        .map(::toAppData)
        .toList()

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

private fun PackageManager.isNotGame(resolveInfo: ResolveInfo) = !isGame(resolveInfo)

// Not all games use flag or category
@Suppress("DEPRECATION")
private fun PackageManager.isGame(resolveInfo: ResolveInfo): Boolean {
    val applicationInfo = getApplicationInfoCompat(resolveInfo.activityInfo.packageName)
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
        applicationInfo.flags and ApplicationInfo.FLAG_IS_GAME > 0
    } else {
        applicationInfo.category == ApplicationInfo.CATEGORY_GAME
    }
}

@Suppress("DEPRECATION")
private fun PackageManager.getApplicationInfoCompat(packageName: String, flag: Int = 0): ApplicationInfo {
    return  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
        getApplicationInfo(packageName, flag)
    } else {
        getApplicationInfo(packageName, PackageManager.ApplicationInfoFlags.of(flag.toLong()))
    }
}