package com.zigis.paleontologas.application.ui.progressbar.interfaces

interface ProgressAnimationListener {
    fun onAnimationStart()
    fun onAnimationFinish()
    fun onAnimationProgress(progress: Int)
}