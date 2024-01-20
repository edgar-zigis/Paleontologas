package com.zigis.paleontologas.core.managers

import android.content.Context
import android.content.pm.PackageManager

class ApplicationVersionManager constructor(
    private val context: Context
) {
    fun getApplicationVersionName(): String {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).versionName
        } catch (e: PackageManager.NameNotFoundException) {
            "unknown"
        }
    }
}