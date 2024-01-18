package com.zigis.paleontologas.other.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.architecture.BaseView
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewLanguageBinding
import com.zigis.paleontologas.other.adapters.LanguageListAdapter
import com.zigis.paleontologas.other.viewmodels.LocaleConfiguration
import java.util.*

interface LanguageViewDelegate {
    fun onLocaleSelected(locale: Locale)
    fun onBackInvoked()
}

class LanguageView(context: Context) : BaseView(context) {

    var delegate: LanguageViewDelegate? = null

    override val binding = ViewLanguageBinding.inflate(layoutInflater)

    private val adapter = LanguageListAdapter {
        delegate?.onLocaleSelected(it)
    }

    init {
        with(binding) {
            title.text = getString(R.string.language)
            backButton.setDebounceClickListener {
                delegate?.onBackInvoked()
            }
            languageList.layoutManager = LinearLayoutManager(context)
            languageList.adapter = adapter
        }
        addView(binding.root)
    }

    fun setLocaleConfiguration(configuration: LocaleConfiguration) = with(binding) {
        adapter.currentLocale = configuration.first
        adapter.updateItems(configuration.second)
    }
}