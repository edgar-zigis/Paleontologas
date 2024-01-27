package com.zigis.paleontologas.features.main.routers

import android.content.Intent
import com.zigis.paleontologas.core.architecture.BaseRouter
import com.zigis.paleontologas.core.providers.AndroidLifecycleProvider
import com.zigis.paleontologas.core.routers.GlobalRouter
import com.zigis.paleontologas.features.main.MainActivity
import com.zigis.paleontologas.features.main.stories.about.AboutFragment
import com.zigis.paleontologas.features.main.stories.language.LanguageFragment

class MainRouter(
    override val globalRouter: GlobalRouter,
    private val androidLifecycleProvider: AndroidLifecycleProvider
) : BaseRouter(globalRouter) {

    fun openMainScreen() {
        val context = androidLifecycleProvider.getActiveContext()
        Intent(context, MainActivity::class.java).apply {
            context.startActivity(this)
        }
        androidLifecycleProvider.getActivity()?.finish()
    }

    fun openAboutSection() {
        globalRouter.pushFragment(
            AboutFragment()
        )
    }

    fun openLanguages() {
        globalRouter.pushFragment(
            LanguageFragment()
        )
    }
}