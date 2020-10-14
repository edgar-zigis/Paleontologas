package com.zigis.paleontologas.other.fragments

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.application.android.BaseViewModelFragment
import com.zigis.paleontologas.main.activities.MainActivity
import com.zigis.paleontologas.other.viewmodels.LanguageViewModel
import com.zigis.paleontologas.other.views.LanguageView
import com.zigis.paleontologas.other.views.LanguageViewDelegate
import java.util.*

class LanguageFragment : BaseViewModelFragment<LanguageViewModel, LanguageView>(),
    LanguageViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): LanguageView {
        return LanguageView(inflater.context).also {
            it.delegate = this
            it.onBack = {
                activity?.onBackPressed()
            }
        }.also {
            viewModel.loadLocaleList()
        }
    }

    override fun observeChanges() {
        viewModel.localeConfiguration.observe(viewLifecycleOwner) {
            contentView.setLocaleConfiguration(it)
        }
        viewModel.updatedLocale.observe(viewLifecycleOwner) {
            activity?.finish()
            startActivity(Intent(activity, MainActivity::class.java))
        }
    }

    //  LanguageViewDelegate

    override fun onLocaleSelected(locale: Locale) {
        viewModel.changeLocale(locale)
    }
}