package com.example.football.presentation.main.matches.statistics.adapter

import android.view.View
import com.example.football.R
import com.example.football.data.model.StatisticsList
import com.example.football.presentation.base.RecyclerAdapter
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class StatisticsAdapter @Inject constructor() :
  RecyclerAdapter<StatisticsList, StatisticsViewHolder>() {

  override fun getLayoutId(viewType: Int) = R.layout.item_match_statistic

  override fun instantiateViewHolder(itemView: View, viewType: Int) = StatisticsViewHolder(itemView)

  override fun setData(newItems: List<StatisticsList>) {
    adapterItems.addAll(newItems)
  }

  override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
    holder.bind(adapterItems[position])
  }
}