package com.zigis.paleontologas.application.android

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<T>(
    open val items: List<T>
) : RecyclerView.Adapter<BaseListAdapter<T>.ViewHolder>() {

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    abstract fun onBindViewHolder(view: View, item: T)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder.view, items[position])
    }

    override fun getItemCount() = items.size
}