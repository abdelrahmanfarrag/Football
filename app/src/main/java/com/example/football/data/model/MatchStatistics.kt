package com.example.football.data.model

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
data class MatchStatistics(
  val success: Boolean,
  val statistics: Statistics
) {

  data class Statistics(
    val attacks: String,
    val attemptsOnGoal: String,
    val corners: String,
    val dangerousAttacks: String,
    val fouls: String,
    val freeKicks: String,
    val offsides: String,
    val possession: String,
    val yellowCards: String,
    val redCards: String,
    val shotsOnTarget: String,
    val shotsOffTarget: String
  )
}