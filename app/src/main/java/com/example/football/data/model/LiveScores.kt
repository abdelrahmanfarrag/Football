package com.example.football.data.model

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
data class LiveScores(
  val success: Boolean,
  val data: Data
) {
  data class Data(
    val matches: List<Match>
  )

  data class Match(
    val awayName: String,
    val awayId: Int,
    val competitionName: String,
    val competitionId: Int,
    val etScore: String,
    val ftScore: String,
    val homeName: String,
    val homeId: Int,
    val htScore: String,
    val lastChanged: String,
    val leagueName: String,
    val location: String,
    val score: String,
    val status: String,
    val time: String

  )
}