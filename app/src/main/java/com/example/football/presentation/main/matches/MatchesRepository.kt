package com.example.football.presentation.main.matches

import com.example.football.data.Constants.API_KEY
import com.example.football.data.Constants.KEY
import com.example.football.data.Constants.SECRET
import com.example.football.data.Constants.SECRET_KEY
import com.example.football.data.model.LiveScores
import com.example.football.data.remote.LiveScoreApi
import com.example.football.data.remote.Validator
import com.example.football.data.remote.response.mapToLiveScores
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
class MatchesRepository @Inject constructor(
  private val liveScoreApi: LiveScoreApi,
  private val validator: Validator
) {

  fun getLiveMapScore(): Single<ResponseResource<LiveScores>> {
    return liveScoreApi.loadLiveScores(providesQueryMap())
      .map { validator.validateApiResponse(it) }
      .map { liveScoresResponse ->
        liveScoresResponse.responseData?.let { liveScores ->
          ResponseResource(SUCCESS, responseData = liveScores.mapToLiveScores())
        } ?: ResponseResource(ERROR, message = Error.GENERAL)
      }
  }

  private fun providesQueryMap(): MutableMap<String, String> =
    mutableMapOf<String, String>().apply {
      this[SECRET] = SECRET_KEY
      this[KEY] = API_KEY
    }

}