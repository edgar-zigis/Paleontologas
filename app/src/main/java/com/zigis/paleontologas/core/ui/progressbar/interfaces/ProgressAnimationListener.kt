package com.zigis.paleontologas.core.ui.progressbar.interfaces

interface ProgressAnimationListener {
    fun onAnimationStart()
    fun onAnimationFinish()
    fun onAnimationProgress(progress: Int)
}