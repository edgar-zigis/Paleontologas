package com.zigis.paleontologas.core.architecture

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseListAdapter<T, V : ViewBinding>(
    open var items: List<T>
) : RecyclerView.Adapter<BaseListAdapter.ViewHolder>() {

    protected lateinit var context: Context

    class ViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root)

    abstract fun onBindViewHolder(binding: V, item: T, position: Int)
    abstract fun onBindingRequested(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): V

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(onBindingRequested(inflater, parent, viewType))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBindViewHolder(holder.binding as V, items[position], position)
    }

    override fun getItemCount() = items.size

    open fun updateItems(items: List<T>) {
        if (this.items == items) return
        this.items = items
        notifyDataSetChanged()
    }
}