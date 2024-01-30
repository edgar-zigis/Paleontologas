package com.zigis.paleontologas.features.library.stories.geologicalperiod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.core.architecture.BaseListAdapter
import com.zigis.paleontologas.core.extensions.getDrawable
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.features.library.databinding.AdapterGeologicalPeriodBinding

class GeologicalPeriodListAdapter(
    override var items: List<GeologicalPeriodListAdapterItem> = emptyList(),
    private val onClick: (Int) -> Unit
) : BaseListAdapter<GeologicalPeriodListAdapterItem, AdapterGeologicalPeriodBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): AdapterGeologicalPeriodBinding {
        return AdapterGeologicalPeriodBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: AdapterGeologicalPeriodBinding,
        item: GeologicalPeriodListAdapterItem,
        position: Int
    ) = with(binding) {
        root.apply {
            setDebounceClickListener {
                onClick(item.id)
            }
        }
        image.setImageDrawable(context.getDrawable(item.thumbnail))
        title.text = context.getString(item.title)
    }
}