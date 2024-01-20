package com.zigis.paleontologas.features.main.stories.language

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.features.main.MainActivity
import java.util.Locale

class LanguageFragment : BaseFragment<LanguageViewModel, LanguageView>(),
    LanguageViewDelegate {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?): LanguageView {
        return LanguageView(inflater.context).also {
            it.delegate = this
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

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}