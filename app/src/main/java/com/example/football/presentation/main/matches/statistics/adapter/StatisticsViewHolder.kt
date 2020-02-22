package com.example.football.presentation.main.matches.statistics.adapter

import android.view.View
import com.example.football.data.model.StatisticsList
import com.example.football.presentation.base.RecyclerAdapter.BaseViewHolder
import com.example.football.utils.makeProgress
import kotlinx.android.synthetic.main.item_match_statistic.view.awayValueTextValue
import kotlinx.android.synthetic.main.item_match_statistic.view.homeValueTextView
import kotlinx.android.synthetic.main.item_match_statistic.view.sectionNameTextView
import kotlinx.android.synthetic.main.item_match_statistic.view.statisticProgress

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class StatisticsViewHolder(itemView: View) : BaseViewHolder<StatisticsList>(itemView) {

  private val sectionName = itemView.sectionNameTextView
  private val home = itemView.homeValueTextView
  private val away = itemView.awayValueTextValue
  private val progressView = itemView.statisticProgress
  override fun bind(item: StatisticsList) {
    sectionName.text = item.sectionName
    home.text = "${item.homeTeamValue}"
    away.text = "${item.awayTeamValue}"
    progressView.progress =
      makeProgress(item.homeTeamValue.toDouble(), item.awayTeamValue.toDouble())
  }
}