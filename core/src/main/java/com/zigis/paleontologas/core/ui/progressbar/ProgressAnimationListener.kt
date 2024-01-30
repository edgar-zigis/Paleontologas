package com.zigis.paleontologas.core.ui.progressbar

interface ProgressAnimationListener {
    fun onAnimationStart()
    fun onAnimationFinish()
    fun onAnimationProgress(progress: Int)
}