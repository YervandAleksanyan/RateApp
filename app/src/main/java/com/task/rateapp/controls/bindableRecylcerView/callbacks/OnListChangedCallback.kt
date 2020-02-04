package com.task.rateapp.controls.bindableRecylcerView.callbacks

import androidx.databinding.ObservableList
import com.task.rateapp.controls.bindableRecylcerView.adapters.BaseBindableRecyclerViewAdapter
import java.lang.ref.WeakReference

open class OnListChangedCallback<T>(
    bindableRecyclerViewAdapter: BaseBindableRecyclerViewAdapter<T>
) :
    ObservableList.OnListChangedCallback<ObservableList<T>>() {

    private val adapterReference: WeakReference<BaseBindableRecyclerViewAdapter<T>> =
        WeakReference(bindableRecyclerViewAdapter)

    override fun onChanged(sender: ObservableList<T>?) {
        val adapter = adapterReference.get()
        adapter?.notifyDataSetChanged()
    }

    override fun onItemRangeRemoved(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {
        val adapter = adapterReference.get()
        adapter?.notifyItemRangeRemoved(positionStart, itemCount)
    }

    override fun onItemRangeMoved(
        sender: ObservableList<T>?,
        positionStart: Int,
        toPosition: Int,
        itemCount: Int
    ) {

        val adapter = adapterReference.get()
        adapter?.notifyItemRangeRemoved(positionStart, itemCount)
        adapter?.notifyItemRangeInserted(toPosition, itemCount)
    }

    override fun onItemRangeInserted(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {

        val adapter = adapterReference.get()
        adapter?.notifyItemRangeInserted(positionStart, itemCount)
    }

    override fun onItemRangeChanged(
        sender: ObservableList<T>?,
        positionStart: Int,
        itemCount: Int
    ) {

        val adapter = adapterReference.get()
        adapter?.notifyItemRangeChanged(positionStart, itemCount)
    }
}