package com.example.football.presentation.main.videos.adapter

import android.view.View
import com.example.football.R
import com.example.football.data.model.Videos
import com.example.football.presentation.base.RecyclerAdapter
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class VideosAdapter @Inject constructor() : RecyclerAdapter<Videos, VideosViewHolder>() {
  override fun getLayoutId(viewType: Int): Int = R.layout.item_videos
  override fun instantiateViewHolder(itemView: View, viewType: Int) = VideosViewHolder(itemView)

  override fun setData(newItems: List<Videos>) {
    adapterItems.addAll(newItems)
  }

  override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
    holder.bind(adapterItems[position])
  }
}