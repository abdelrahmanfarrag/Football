package com.example.football.data.remote

import com.example.football.data.Constants.Urls.LIVE_SCORE
import com.example.football.data.Constants.Urls.STATISTICS
import com.example.football.data.remote.response.LiveScoresResponse
import com.example.football.data.remote.response.MatchStatisticsResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
interface ScoreBatApi {

}

interface LiveScoreApi {

  @GET(LIVE_SCORE)
  fun loadLiveScores(
    @QueryMap queryMap: Map<String, String>
  ): Single<Response<LiveScoresResponse>>

  @GET(STATISTICS)
  fun loadMatchStatistics(
    @QueryMap queryMap: Map<String, String>
  ): Single<Response<MatchStatisticsResponse>>

}