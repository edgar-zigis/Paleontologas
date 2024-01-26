package com.zigis.paleontologas.features.main.stories.language

import android.content.Context
import com.zigis.paleontologas.core.architecture.BaseFragment
import com.zigis.paleontologas.core.architecture.interfaces.IView
import com.zigis.paleontologas.core.extensions.sendSafely
import org.koin.android.ext.android.inject
import java.util.Locale

class LanguageFragment : BaseFragment<LanguageViewState, LanguageIntent, LanguageViewModel>(),
    LanguageViewDelegate {

    override val viewModel: LanguageViewModel by inject()

    override fun onCreateView(context: Context): IView<LanguageViewState> {
        return LanguageView(context).also {
            it.delegate = this
        }.also {
            viewModel.intents.sendSafely(LanguageIntent.Initialize)
        }
    }

    //  LanguageViewDelegate

    override fun onLocaleSelected(locale: Locale) {
        viewModel.intents.sendSafely(LanguageIntent.ChangeLocale(locale = locale))
    }

    override fun onBackInvoked() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }
}