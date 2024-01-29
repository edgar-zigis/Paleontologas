package com.zigis.paleontologas.features.main.stories.language.adapter

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseListAdapter
import com.zigis.paleontologas.core.extensions.getColorIntWithAlpha
import com.zigis.paleontologas.core.extensions.getDrawable
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.AdapterLanguageBinding
import java.util.Locale

class LanguageListAdapter(
    var currentLocale: Locale? = null,
    override var items: List<Locale> = emptyList(),
    private val onClick: (Locale) -> Unit
) : BaseListAdapter<Locale, AdapterLanguageBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): AdapterLanguageBinding {
        return AdapterLanguageBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: AdapterLanguageBinding,
        item: Locale,
        position: Int
    ) = with(binding) {
        root.apply {
            background = if (currentLocale == item) {
                ColorDrawable(context.getColorIntWithAlpha(R.color.colorPrimaryDark, 0.08f))
            } else null
            setDebounceClickListener {
                onClick(item)
            }
        }

        val identifier = if (!item.country.isNullOrEmpty()) {
            item.country.lowercase()
        } else item.language

        flagIcon.setImageDrawable(context.getDrawable("ic_flag_$identifier"))
        language.text = context.getString("language_$identifier")
    }
}