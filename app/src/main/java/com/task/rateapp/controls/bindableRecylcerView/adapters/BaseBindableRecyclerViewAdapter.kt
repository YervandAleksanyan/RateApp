package com.task.rateapp.controls.bindableRecylcerView.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.task.rateapp.controls.bindableRecylcerView.ViewHolder
import com.task.rateapp.controls.bindableRecylcerView.bindings.ItemBinder
import com.task.rateapp.controls.bindableRecylcerView.callbacks.OnListChangedCallback
import com.task.rateapp.controls.bindableRecylcerView.eventHandlers.ClickHandler
import com.task.rateapp.controls.bindableRecylcerView.eventHandlers.LongClickHandler

abstract class BaseBindableRecyclerViewAdapter<T>(
    newItems: Collection<T>?,
    var itemBinder: ItemBinder<T>
) : RecyclerView.Adapter<ViewHolder>(), View.OnClickListener,
    View.OnLongClickListener {

    abstract val onListChangedCallback: OnListChangedCallback<T>
    abstract val itemModel: Int

    private lateinit var inflater: LayoutInflater
    protected var items: ObservableArrayList<T>? = null
    var longClickHandler: LongClickHandler<T>? = null
    var clickHandler: ClickHandler<T>? = null

    fun setItems(newItems: Collection<T>?) {
        if (this.items == newItems) {
            return
        }

        this.items?.let {
            it.removeOnListChangedCallback(onListChangedCallback)
            notifyItemRangeRemoved(0, it.size)
        }
        when {
            newItems is ObservableList<*> -> {
                this.items = newItems as ObservableArrayList<T>?
                notifyItemRangeInserted(0, this.items!!.size)
                this.items?.addOnListChangedCallback(onListChangedCallback)
            }
            newItems != null -> {
                this.items = ObservableArrayList()
                this.items!!.addOnListChangedCallback(onListChangedCallback)
                this.items!!.addAll(newItems)
            }
            else -> this.items = null
        }
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        items?.removeOnListChangedCallback(onListChangedCallback)
    }

    //ViewHolder
    override fun onCreateViewHolder(viewGroup: ViewGroup, layoutId: Int): ViewHolder {
        inflater = LayoutInflater.from(viewGroup.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layoutId, viewGroup, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items!![position]
        itemBinder.resetViewHolder(viewHolder)
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item)
        viewHolder.binding.root.setTag(itemModel, item)
        viewHolder.binding.root.setOnClickListener(this)
        viewHolder.binding.root.setOnLongClickListener(this)
        viewHolder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int = itemBinder.getLayoutRes(items!![position])

    override fun getItemCount(): Int = items?.size ?: 0

    //Click Events
    override fun onClick(v: View) {
        clickHandler?.let {
            val item = v.getTag(itemModel) as T
            clickHandler!!.onClick(item, v)
        }

    }

    override fun onLongClick(v: View): Boolean {
        longClickHandler?.let {
            val item = v.getTag(itemModel) as T
            longClickHandler!!.onLongClick(item)
            return true
        }
        return false
    }

}