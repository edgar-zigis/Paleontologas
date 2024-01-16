package com.zigis.paleontologas.other.views

import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.databinding.ViewLanguageBinding
import com.zigis.paleontologas.other.adapters.LanguageListAdapter
import com.zigis.paleontologas.other.viewmodels.LocaleConfiguration
import java.util.*

interface LanguageViewDelegate {
    fun onLocaleSelected(locale: Locale)
}

class LanguageView(context: Context) : BaseView<ViewLanguageBinding>(
    context,
    ViewLanguageBinding.inflate(LayoutInflater.from(context))
) {
    override val titleTextResId: Int = R.string.language

    var delegate: LanguageViewDelegate? = null

    private val adapter = LanguageListAdapter {
        delegate?.onLocaleSelected(it)
    }

    override fun initialize() = with(viewBinding) {
        languageList.layoutManager = LinearLayoutManager(context)
        languageList.adapter = adapter
    }

    fun setLocaleConfiguration(configuration: LocaleConfiguration) = with(viewBinding) {
        adapter.currentLocale = configuration.first
        adapter.updateItems(configuration.second)
    }
}