package com.zigis.paleontologas.features.main.stories.home

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeViewState, HomeIntent, HomeViewModel>(), HomeViewDelegate {

    override val viewModel: HomeViewModel by inject()

    override fun onCreateView(context: Context): IView<HomeViewState> {
        return HomeView(context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        super.onAttached()
        viewModel.intents.sendSafely(HomeIntent.Initialize)
    }

    //  MainMenuViewDelegate

    override fun openPeriod(periodId: Int) {
        viewModel.intents.sendSafely(HomeIntent.OpenPeriod(periodId = periodId))
    }

    override fun openQuiz() {
        viewModel.intents.sendSafely(HomeIntent.OpenQuiz)
    }

    override fun openLanguages() {
        viewModel.intents.sendSafely(HomeIntent.OpenLanguages)
    }

    override fun openAbout() {
        viewModel.intents.sendSafely(HomeIntent.OpenAbout)
    }
}