package com.zigis.paleontologas.periods.adapters

import android.view.View
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.inflate
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.periods.data.entities.LifeForm
import kotlinx.android.synthetic.main.view_lifeforms_list_item.view.*

class LifeFormListAdapter(
    override val items: List<LifeForm>,
    private val onClick: (LifeForm) -> Unit
) : BaseListAdapter<LifeForm>(items) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.view_lifeforms_list_item))
    }

    override fun onBindViewHolder(view: View, item: LifeForm) = with(view) {
        image.setImageDrawable(context.getDrawable(item.thumbnail))
        title.text = context.getString(item.title)

        setDebounceClickListener {
            onClick(item)
        }
    }
}