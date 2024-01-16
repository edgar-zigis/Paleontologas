package com.zigis.paleontologas.periods.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewPeriodsListItemBinding
import com.zigis.paleontologas.periods.data.entities.Period

class PeriodListAdapter(
    override var items: List<Period> = emptyList(),
    private val onClick: (Period) -> Unit
) : BaseListAdapter<Period, ViewPeriodsListItemBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewPeriodsListItemBinding {
        return ViewPeriodsListItemBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: ViewPeriodsListItemBinding,
        item: Period,
        position: Int
    ) = with(binding) {
        root.apply {
            setDebounceClickListener {
                onClick(item)
            }
        }

        image.setImageDrawable(context.getDrawable(item.thumbnail))

        title.text = context.getString(item.title)
        description.text = context.getString(item.shortDescription)
        year.text = context.getString(R.string.mya, item.timeScale)

        progressBar.title = "${item.quizProgress}%"
        progressBar.progress = item.quizProgress
        progressBar.animateProgressTo(0, item.quizProgress)
    }
}