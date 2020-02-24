package com.example.football.presentation.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.football.presentation.base.RecyclerAdapter.BaseViewHolder
import com.example.football.utils.extensions.inflate

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
abstract class RecyclerAdapter<T, H : BaseViewHolder<T>> : RecyclerView.Adapter<H>() {

  protected var adapterItems = mutableListOf<T>()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    instantiateViewHolder(parent.inflate(getLayoutId(viewType)), viewType)

  fun clear() {
    adapterItems.clear()
    notifyItemRangeChanged(0, itemCount)
  }

  override fun getItemCount() = adapterItems.size

  protected open fun getItemAtPosition(position: Int) = adapterItems[position]

  @LayoutRes protected abstract fun getLayoutId(viewType: Int): Int

  protected abstract fun instantiateViewHolder(itemView: View, viewType: Int): H

  abstract fun setData(newItems: List<T>)


  abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
     abstract fun bind(item: T)
  }
}