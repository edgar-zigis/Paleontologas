package com.zigis.paleontologas.core.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.zigis.paleontologas.core.extensions.isRTL

class GridSpacingItemDecoration(
    private val columns: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % columns
        if (includeEdge) {
            outRect.left = spacing - column * spacing / columns
            outRect.right = (column + 1) * spacing / columns
            if (position < columns) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            if (view.isRTL()) {
                outRect.left = spacing - (column + 1) * spacing / columns
                outRect.right = column * spacing / columns
            } else {
                outRect.left = column * spacing / columns
                outRect.right = spacing - (column + 1) * spacing / columns
            }
            if (position >= columns) {
                outRect.top = spacing
            }
        }
    }
}