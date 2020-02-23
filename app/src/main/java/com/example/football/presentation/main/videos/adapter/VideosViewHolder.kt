package com.example.football.presentation.main.videos.adapter

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.example.football.data.model.Videos
import com.example.football.presentation.base.RecyclerAdapter.BaseViewHolder
import com.example.football.utils.extensions.formatDate
import com.example.football.utils.extensions.load
import kotlinx.android.synthetic.main.item_videos.view.mediaContainer
import kotlinx.android.synthetic.main.item_videos.view.thumbnail
import kotlinx.android.synthetic.main.item_videos.view.videoCompetitionAwayName
import kotlinx.android.synthetic.main.item_videos.view.videoCompetitionDate
import kotlinx.android.synthetic.main.item_videos.view.videoCompetitionHomeName
import kotlinx.android.synthetic.main.item_videos.view.videoCompetitionName
import kotlinx.android.synthetic.main.item_videos.view.videoCompetitionTitle
import kotlinx.android.synthetic.main.item_videos.view.videoLoadingFrame
import kotlinx.android.synthetic.main.item_videos.view.volumeController

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class VideosViewHolder(itemView: View) : BaseViewHolder<Videos>(itemView) {

  private val competitionName: TextView = itemView.videoCompetitionName
  private val title: TextView = itemView.videoCompetitionTitle
  private val date: TextView = itemView.videoCompetitionDate
  private val homeName: TextView = itemView.videoCompetitionHomeName
  private val awayName: TextView = itemView.videoCompetitionAwayName
  val thumbnail: ImageView = itemView.thumbnail
  val loadingFrame: View = itemView.videoLoadingFrame
  val volumeController: ImageView = itemView.volumeController
  override fun bind(item: Videos) {
    competitionName.text = item.competition.name
    title.text = item.title
    date.formatDate(item.date)
    itemView.tag = this

    homeName.text = item.side1.name
    awayName.text = item.side2.name
    thumbnail.load(item.thumbnail)
  }
}