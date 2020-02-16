package com.zigis.paleontologas.other.views

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseView
import com.zigis.paleontologas.other.adapters.LanguageListAdapter
import com.zigis.paleontologas.other.viewmodels.LocaleConfiguration
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.view_language.view.*
import java.util.*

interface LanguageViewDelegate {
    fun onLocaleSelected(locale: Locale)
}

class LanguageView(context: Context) : BaseView(context, R.layout.view_language) {

    var delegate: LanguageViewDelegate? = null

    override fun initialize() {
        title.text = context.getString(R.string.language)
    }

    fun setLocaleConfiguration(configuration: LocaleConfiguration) {
        languageList.layoutManager = LinearLayoutManager(context)
        languageList.adapter = LanguageListAdapter(configuration.first, configuration.second) {
            delegate?.onLocaleSelected(it)
        }
    }
}