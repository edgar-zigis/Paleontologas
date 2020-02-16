package com.zigis.paleontologas.other.adapters

import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.*
import kotlinx.android.synthetic.main.view_language_list_item.view.*
import java.util.*

class LanguageListAdapter(
    private val currentLocale: Locale?,
    override val items: List<Locale>,
    private val onClick: (Locale) -> Unit
) : BaseListAdapter<Locale>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_language_list_item))
    }

    override fun onBindViewHolder(view: View, item: Locale) = with(view) {
        flagIcon.setImageDrawable(
            context.getDrawable("ic_flag_${item.language}")
        )
        language.text = context.getString("language_${item.language}")

        background = if (currentLocale == item) {
            ColorDrawable(context.getColorIntWithAlpha(R.color.colorPrimaryDark, 0.08f))
        } else {
            null
        }

        setDebounceClickListener {
            onClick(item)
        }
    }
}