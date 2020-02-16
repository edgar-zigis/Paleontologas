package com.zigis.paleontologas.periods.adapters

import android.view.View
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.inflate
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.periods.data.entities.Period
import kotlinx.android.synthetic.main.view_periods_list_item.view.*

class PeriodListAdapter(
    override var items: List<Period>,
    private val onClick: (Period) -> Unit
) : BaseListAdapter<Period>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_periods_list_item))
    }

    override fun onBindViewHolder(view: View, item: Period) = with(view) {
        image.setImageDrawable(context.getDrawable(item.thumbnail))

        title.text = context.getString(item.title)
        description.text = context.getString(item.shortDescription)
        year.text = item.timeScale

        progressBar.title = "${item.quizProgress}%"
        progressBar.progress = item.quizProgress
        progressBar.animateProgressTo(0, item.quizProgress)

        setDebounceClickListener {
            onClick(item)
        }
    }
}