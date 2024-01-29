package com.zigis.paleontologas.features.main.stories.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zigis.paleontologas.R
import com.zigis.paleontologas.core.architecture.BaseListAdapter
import com.zigis.paleontologas.core.extensions.getDrawable
import com.zigis.paleontologas.core.extensions.getString
import com.zigis.paleontologas.core.extensions.setDebounceClickListener
import com.zigis.paleontologas.databinding.AdapterHomeBinding

class HomeListAdapter(
    override var items: List<HomeListAdapterItem> = emptyList(),
    private val onClick: (Int) -> Unit
) : BaseListAdapter<HomeListAdapterItem, AdapterHomeBinding>(items) {

    override fun onBindingRequested(
        inflater: LayoutInflater,
        parent: ViewGroup,
        viewType: Int
    ): AdapterHomeBinding {
        return AdapterHomeBinding.inflate(inflater, parent, false)
    }

    override fun onBindViewHolder(
        binding: AdapterHomeBinding,
        item: HomeListAdapterItem,
        position: Int
    ) = with(binding) {
        root.apply {
            setDebounceClickListener {
                onClick(item.id)
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