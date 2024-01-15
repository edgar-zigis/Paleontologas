package com.zigis.paleontologas.other.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.*
import com.zigis.paleontologas.databinding.ViewLanguageListItemBinding
import java.util.Locale

class LanguageListAdapter(
    private val currentLocale: Locale? = null,
    override var items: List<Locale> = emptyList(),
    private val onClick: (Locale) -> Unit
) : BaseListAdapter<Locale, ViewLanguageListItemBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewLanguageListItemBinding {
        return ViewLanguageListItemBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: ViewLanguageListItemBinding,
        item: Locale,
        position: Int
    ) {
        with(binding) {
            val identifier = if (!item.country.isNullOrEmpty()) {
                item.country.lowercase()
            } else item.language

            flagIcon.setImageDrawable(context.getDrawable("ic_flag_$identifier"))
            language.text = context.getString("language_$identifier")

            root.background = if (currentLocale == item) {
                ColorDrawable(context.getColorIntWithAlpha(R.color.colorPrimaryDark, 0.08f))
            } else {
                null
            }

            root.setDebounceClickListener {
                onClick(item)
            }
        }
    }
}