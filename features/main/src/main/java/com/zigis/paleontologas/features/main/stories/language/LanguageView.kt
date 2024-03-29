package com.zigis.paleontologas.features.main.stories.language

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.zigis.paleontologas.core.architecture.BaseView
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.features.main.databinding.FragmentLanguageBinding
import com.zigis.paleontologas.features.main.stories.language.adapter.LanguageListAdapter

class LanguageView(
    context: Context
) : BaseView<LanguageViewState, FragmentLanguageBinding>(context) {

    var delegate: LanguageViewDelegate? = null

    override var binding: FragmentLanguageBinding? = FragmentLanguageBinding.inflate(layoutInflater)

    private val adapter = LanguageListAdapter {
        delegate?.onLocaleSelected(it)
    }

    init {
        with(requireBinding()) {
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            languageList.layoutManager = LinearLayoutManager(context)
            languageList.adapter = adapter
        }
        addView(requireBinding().root)
    }

    override fun render(state: LanguageViewState) {
        adapter.currentLocale = state.currentLocale
        adapter.updateItems(state.localeList)
    }
}