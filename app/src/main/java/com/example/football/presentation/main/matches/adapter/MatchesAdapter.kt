package com.example.football.presentation.main.matches.adapter

import android.util.Log
import android.view.View
import com.example.football.R
import com.example.football.data.model.LiveScores
import com.example.football.data.model.LiveScores.Match
import com.example.football.presentation.base.RecyclerAdapter
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class MatchesAdapter @Inject constructor() : RecyclerAdapter<Match, MatchesViewHolder>() {

  private lateinit var matchClick: (Int, View, Match) -> Unit
  override fun getLayoutId(viewType: Int) = R.layout.item_live_score

  override fun setData(newItems: List<Match>) {
    adapterItems.addAll(newItems)

  }

  fun setMatchClickListener(matchClick: (Int, View, Match) -> Unit) {
    this.matchClick = matchClick
  }

  override fun instantiateViewHolder(itemView: View, viewType: Int) =
    MatchesViewHolder(itemView, matchClick)

  override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
    holder.bind(adapterItems[position])
  }

}