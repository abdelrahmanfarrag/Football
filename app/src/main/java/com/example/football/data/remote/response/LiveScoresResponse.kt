package com.example.football.data.remote.response


import com.example.football.data.model.LiveScores
import com.example.football.data.model.LiveScores.Data
import com.example.football.data.model.LiveScores.Match
import com.google.gson.annotations.SerializedName

data class LiveScoresResponse(
  @SerializedName("data")
  val data: Data,
  @SerializedName("success")
  val success: Boolean
) {
  data class Data(
    @SerializedName("match")
    val match: List<Match>
  ) {
    data class Match(
      @SerializedName("added")
      val added: String,
      @SerializedName("away_id")
      val awayId: Int,
      @SerializedName("away_name")
      val awayName: String,
      @SerializedName("competition_id")
      val competitionId: Int,
      @SerializedName("competition_name")
      val competitionName: String,
      @SerializedName("et_score")
      val etScore: String,
      @SerializedName("events")
      val events: Boolean,
      @SerializedName("fixture_id")
      val fixtureId: Int,
      @SerializedName("ft_score")
      val ftScore: String,
      @SerializedName("home_id")
      val homeId: Int,
      @SerializedName("home_name")
      val homeName: String,
      @SerializedName("ht_score")
      val htScore: String,
      @SerializedName("id")
      val id: Int,
      @SerializedName("last_changed")
      val lastChanged: String,
      @SerializedName("league_id")
      val leagueId: Int,
      @SerializedName("league_name")
      val leagueName: String,
      @SerializedName("location")
      val location: String,
      @SerializedName("outcomes")
      val outcomes: Outcomes,
      @SerializedName("scheduled")
      val scheduled: String,
      @SerializedName("score")
      val score: String,
      @SerializedName("status")
      val status: String,
      @SerializedName("time")
      val time: String
    ) {
      data class Outcomes(
        @SerializedName("extra_time")
        val extraTime: Any,
        @SerializedName("full_time")
        val fullTime: Any,
        @SerializedName("half_time")
        val halfTime: Any
      )
    }
  }
}

fun LiveScoresResponse.mapToLiveScores() = LiveScores(success, Data(data.match.map {
  Match(
    it.awayName,
    it.awayId,
    it.competitionName,
    it.competitionId,
    it.etScore,
    it.ftScore,
    it.homeName,
    it.homeId,
    it.htScore,
    it.lastChanged,
    it.leagueName,
    it.location,
    it.score,
    it.status,
    it.time
  )

}))