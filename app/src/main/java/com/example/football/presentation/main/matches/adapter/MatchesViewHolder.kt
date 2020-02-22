package com.example.football.presentation.main.matches.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import com.example.football.data.Constants.IN_PLAY
import com.example.football.data.model.LiveScores
import com.example.football.data.model.LiveScores.Match
import com.example.football.presentation.base.RecyclerAdapter.BaseViewHolder
import com.example.football.utils.extensions.fadeAnimation
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.item_live_score.view.awayTeamText
import kotlinx.android.synthetic.main.item_live_score.view.competitionLocationText
import kotlinx.android.synthetic.main.item_live_score.view.competitionNameText
import kotlinx.android.synthetic.main.item_live_score.view.homeTeamText
import kotlinx.android.synthetic.main.item_live_score.view.liveIndicatorTextView
import kotlinx.android.synthetic.main.item_live_score.view.matchScoreText
import kotlinx.android.synthetic.main.item_live_score.view.statusTextView
import kotlinx.android.synthetic.main.item_live_score.view.timeTextView

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class MatchesViewHolder(itemView: View, private val click: (Int, View, Match) -> Unit) :
  BaseViewHolder<Match>(itemView) {

  private val time = itemView.timeTextView
  private val competitionName = itemView.competitionNameText
  private val competitionLocation = itemView.competitionLocationText
  private val homeTeam = itemView.homeTeamText
  private val matchScore = itemView.matchScoreText
  private val awayTeam = itemView.awayTeamText
  private val status = itemView.statusTextView
  private val live = itemView.liveIndicatorTextView

  @SuppressLint("SetTextI18n")
  override fun bind(item: Match) {
    time.text = "${item.time} '"
    if (item.competitionName.isNotEmpty()) {
      competitionName.text = item.competitionName
    } else {
      competitionName.gone()
    }
    if (item.status == IN_PLAY) {
      live.visible()
      live.fadeAnimation()
    } else {
      live.gone()
    }
    competitionLocation.text = item.location
    homeTeam.text = item.homeName
    matchScore.text = item.score
    awayTeam.text = item.awayName
    status.text = item.status
    itemView.setOnClickListener {
      click.invoke(item.id, itemView, item)
    }
  }
}