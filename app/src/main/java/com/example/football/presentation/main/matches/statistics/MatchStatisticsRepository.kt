package com.example.football.presentation.main.matches.statistics

import com.example.football.data.Constants
import com.example.football.data.Constants.API_KEY
import com.example.football.data.Constants.KEY
import com.example.football.data.Constants.MATCH_ID
import com.example.football.data.Constants.SECRET
import com.example.football.data.Constants.SECRET_KEY
import com.example.football.data.model.MatchStatistics
import com.example.football.data.remote.LiveScoreApi
import com.example.football.data.remote.Validator
import com.example.football.data.remote.response.mapToMatchStatistics
import com.example.football.presentation.common.ResourceState.ERROR
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.common.ResponseResource
import com.example.football.utils.Error
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class MatchStatisticsRepository @Inject constructor(
  private val liveScoreApi: LiveScoreApi,
  private val validator: Validator
) {

  fun loadMatchStatistics(id: Int): Single<ResponseResource<MatchStatistics>> {
    return liveScoreApi.loadMatchStatistics(providesQueryMap(id))
      .map { validator.validateApiResponse(it) }
      .map { response ->
        response.responseData?.let { statistics ->
          ResponseResource(SUCCESS, statistics.mapToMatchStatistics())
        } ?: ResponseResource(ERROR, message = Error.GENERAL)
      }
  }

  private fun providesQueryMap(id: Int): MutableMap<String, String> =
    mutableMapOf<String, String>().apply {
      this[SECRET] = SECRET_KEY
      this[KEY] = API_KEY
      this[MATCH_ID] = id.toString()
    }
}