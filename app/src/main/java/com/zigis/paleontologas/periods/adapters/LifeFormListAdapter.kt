package com.zigis.paleontologas.periods.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.application.android.BaseListAdapter
import com.zigis.paleontologas.application.extensions.getDrawable
import com.zigis.paleontologas.application.extensions.getString
import com.zigis.paleontologas.application.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.ViewLifeformsListItemBinding
import com.zigis.paleontologas.periods.data.entities.LifeForm

class LifeFormListAdapter(
    override var items: List<LifeForm> = emptyList(),
    private val onClick: (LifeForm) -> Unit
) : BaseListAdapter<LifeForm, ViewLifeformsListItemBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): ViewLifeformsListItemBinding {
        return ViewLifeformsListItemBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: ViewLifeformsListItemBinding,
        item: LifeForm,
        position: Int
    ) = with(binding) {
        root.apply {
            setDebounceClickListener {
                onClick(item)
            }
        }

        image.setImageDrawable(context.getDrawable(item.thumbnail))
        title.text = context.getString(item.title)
    }
}