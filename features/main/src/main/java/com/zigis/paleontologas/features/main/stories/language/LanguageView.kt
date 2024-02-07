package com.zigis.paleontologas.features.main.stories.language

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.ui.theme.ApplicationTheme
import com.zigis.paleontologas.features.main.databinding.FragmentComposeBinding
import com.zigis.paleontologas.features.main.stories.language.adapter.LanguageListAdapter

class LanguageView(
    context: Context
) : BaseView<LanguageViewState, FragmentComposeBinding>(context) {

    var delegate: LanguageViewDelegate? = null

    override var binding: FragmentComposeBinding? = FragmentComposeBinding.inflate(layoutInflater)

    private val adapter = LanguageListAdapter {
        delegate?.onLocaleSelected(it)
    }

    init {
        with(requireBinding().rootView) {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                ApplicationTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        LanguageScreen()
                    }
                }
            }
            /*backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            languageList.layoutManager = LinearLayoutManager(context)
            languageList.adapter = adapter*/

        }
        addView(requireBinding().root)
    }

    override fun render(state: LanguageViewState) {
        adapter.currentLocale = state.currentLocale
        adapter.updateItems(state.localeList)
    }
}