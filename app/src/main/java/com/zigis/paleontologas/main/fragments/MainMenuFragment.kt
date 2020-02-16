package com.zigis.paleontologas.main.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.periods.data.entities.Period
import com.zigis.paleontologas.main.viewmodels.MainMenuViewModel
import com.zigis.paleontologas.main.views.MainMenuView
import com.zigis.paleontologas.main.views.MainMenuViewDelegate
import com.zigis.paleontologas.other.fragments.AboutFragment
import com.zigis.paleontologas.other.fragments.LanguageFragment
import com.zigis.paleontologas.periods.fragments.PeriodFragment
import com.zigis.paleontologas.quiz.fragments.QuizProgressFragment

class MainMenuFragment : BaseViewModelFragment<MainMenuViewModel, MainMenuView>(),
    MainMenuViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): MainMenuView {
        return MainMenuView(inflater.context).also {
            it.delegate = this
        }
    }

    override fun onAttached() {
        super.onAttached()
        viewModel.loadPeriods()
    }

    override fun observeChanges() {
        viewModel.periods.observe(viewLifecycleOwner, Observer {
            contentView.setPeriods(items = it)
        })
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