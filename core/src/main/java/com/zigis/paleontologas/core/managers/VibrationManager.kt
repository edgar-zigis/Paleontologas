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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
            getVibrator().vibrate(effect)
        } else {
            getVibrator().vibrate(200)
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