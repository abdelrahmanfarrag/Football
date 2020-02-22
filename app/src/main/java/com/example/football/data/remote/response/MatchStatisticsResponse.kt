package com.example.football.data.remote.response


import com.example.football.data.model.MatchStatistics
import com.example.football.data.model.MatchStatistics.Statistics
import com.google.gson.annotations.SerializedName

data class MatchStatisticsResponse(
  @SerializedName("data")
  val data: Data,
  @SerializedName("success")
  val success: Boolean
) {
  data class Data(
    @SerializedName("attacks")
    val attacks: String? = null,
    @SerializedName("attempts_on_goal")
    val attemptsOnGoal: String? = null,
    @SerializedName("corners")
    val corners: String? = null,
    @SerializedName("dangerous_attacks")
    val dangerousAttacks: String? = null,
    @SerializedName("fauls")
    val fouls: String? = null,
    @SerializedName("free_kicks")
    val freeKicks: String? = null,
    @SerializedName("goal_kicks")
    val goalKicks: String? = null,
    @SerializedName("offsides")
    val offsides: String? = null,
    @SerializedName("penalties")
    val penalties: String? = null,
    @SerializedName("possesion")
    val possession: String? = null,
    @SerializedName("red_cards")
    val redCards: String? = null,
    @SerializedName("saves")
    val saves: String? = null,
    @SerializedName("shots_blocked")
    val shotsBlocked: String? = null,
    @SerializedName("shots_off_target")
    val shotsOffTarget: String? = null,
    @SerializedName("shots_on_target")
    val shotsOnTarget: String? = null,
    @SerializedName("substitutions")
    val substitutions: String? = null,
    @SerializedName("throw_ins")
    val throwIns: String? = null,
    @SerializedName("treatments")
    val treatments: String? = null,
    @SerializedName("yellow_cards")
    val yellowCards: String? = null
  )
}

fun MatchStatisticsResponse.mapToMatchStatistics() = MatchStatistics(
  success, Statistics(
    data.attacks ?: "",
    data.attemptsOnGoal ?: "",
    data.corners ?: "",
    data.dangerousAttacks ?: "",
    data.fouls ?: "",
    data.freeKicks ?: "", data.offsides ?: "",
    data.possession ?: "", data.yellowCards ?: "", data.redCards ?: "",
    data.shotsOnTarget ?: "", data.shotsOffTarget ?: ""
  )
)