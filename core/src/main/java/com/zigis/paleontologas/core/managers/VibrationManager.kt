package com.zigis.paleontologas.core.managers

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

@Suppress("Deprecation")
class VibrationManager(
    private val applicationContext: Context
) {
    fun vibrate() {
        val vibrator = getVibrator()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK))
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                vibrator.vibrate(VibrationEffect.createOneShot(40, 80))
            }
            else -> {
                vibrator.vibrate(40)
            }
        }
    }

    private fun getVibrator(): Vibrator {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
                val manager = applicationContext.getSystemService(VibratorManager::class.java)
                manager.defaultVibrator
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
                applicationContext.getSystemService(Vibrator::class.java)
            }
            else -> {
                applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }
        }
    }
}