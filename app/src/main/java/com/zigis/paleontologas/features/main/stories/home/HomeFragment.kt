package com.zigis.paleontologas.features.main.stories.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.features.library.data.Period
import com.zigis.paleontologas.features.main.stories.about.AboutFragment
import com.zigis.paleontologas.features.main.stories.language.LanguageFragment
import com.zigis.paleontologas.features.library.stories.periods.PeriodFragment
import com.zigis.paleontologas.features.quiz.stories.progress.QuizProgressFragment

class HomeFragment : BaseFragment<HomeViewModel, HomeView>(),
    HomeViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): HomeView {
        return HomeView(inflater.context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        super.onAttached()
        viewModel.loadPeriods()
    }

    override fun observeChanges() {
        viewModel.periods.observe(viewLifecycleOwner) {
            contentView.setPeriods(items = it)
        }
    }

    //  MainMenuViewDelegate

    override fun openPeriod(period: Period) {
        globalRouter.pushFragment(
            PeriodFragment().also { it.periodId = period.id }
        )
    }

    override fun openQuiz() {
        globalRouter.pushFragment(
            QuizProgressFragment()
        )
    }

    override fun openLanguages() {
        globalRouter.pushFragment(
            LanguageFragment()
        )
    }

    override fun openAbout() {
        globalRouter.pushFragment(
            AboutFragment()
        )
    }
}