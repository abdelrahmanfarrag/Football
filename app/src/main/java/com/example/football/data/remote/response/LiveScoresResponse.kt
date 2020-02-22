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
      val added: String? = null,
      @SerializedName("away_id")
      val awayId: Int? = null,
      @SerializedName("away_name")
      val awayName: String? = null,
      @SerializedName("competition_id")
      val competitionId: Int? = null,
      @SerializedName("competition_name")
      val competitionName: String? = null,
      @SerializedName("et_score")
      val etScore: String? = null,
      @SerializedName("events")
      val events: Boolean? = null,
      @SerializedName("fixture_id")
      val fixtureId: Int? = null,
      @SerializedName("ft_score")
      val ftScore: String? = null,
      @SerializedName("home_id")
      val homeId: Int? = null,
      @SerializedName("home_name")
      val homeName: String? = null,
      @SerializedName("ht_score")
      val htScore: String? = null,
      @SerializedName("id")
      val id: Int? = null,
      @SerializedName("last_changed")
      val lastChanged: String? = null,
      @SerializedName("league_id")
      val leagueId: Int? = null,
      @SerializedName("league_name")
      val leagueName: String? = null,
      @SerializedName("location")
      val location: String? = null,
      @SerializedName("outcomes")
      val outcomes: Outcomes? = null,
      @SerializedName("scheduled")
      val scheduled: String? = null,
      @SerializedName("score")
      val score: String? = null,
      @SerializedName("status")
      val status: String? = null,
      @SerializedName("time")
      val time: String? = null
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
    it.awayName ?: "",
    it.awayId ?: 0,
    it.competitionName ?: "",
    it.competitionId ?: 0,
    it.etScore ?: "",
    it.ftScore ?: "",
    it.homeName ?: "",
    it.homeId ?: 0,
    it.htScore ?: "",
    it.lastChanged ?: "",
    it.leagueName ?: "",
    it.location ?: "",
    it.score ?: "",
    it.status ?: "",
    it.time ?: "",
    it.added ?: "",
    it.id ?:0
  )

}))