package com.bavian.apps_collector

interface AppsCollector {
    fun collectGames(): List<AppData>
    fun collectApps(): List<AppData>
}