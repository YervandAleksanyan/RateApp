package com.task.rateapp.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginItemDecoration(
    private val topBottomSpaceHeight: Int = 0,
    private val leftRightSpaceHeight: Int = 0
) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            left = leftRightSpaceHeight
            right = leftRightSpaceHeight
            bottom = topBottomSpaceHeight
        }
    }
}