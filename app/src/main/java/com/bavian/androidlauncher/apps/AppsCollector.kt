package com.bavian.androidlauncher.apps

interface AppsCollector {
    fun collectGames(): List<AppData>
    fun collectApps(): List<AppData>
}