package com.example.football.presentation.main.videos

import com.example.football.data.model.Videos
import com.example.football.data.remote.ScoreBatApi
import com.example.football.data.remote.Validator
import com.example.football.data.remote.response.mapToVideos
import com.example.football.presentation.common.ResourceState.ERROR
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.common.ResponseResource
import com.example.football.utils.Error
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class VideosRepository @Inject constructor(
  private val scoreBatApi: ScoreBatApi,
  private val validator: Validator
) {

  fun getVideos(): Single<ResponseResource<List<Videos>>> {
    return scoreBatApi.loadVideos().map { validator.validateApiResponse(it) }
      .map { videosResponse ->
        videosResponse.responseData?.let { videos ->
          ResponseResource(SUCCESS, responseData = videos.map {
            it.mapToVideos()
          })
        } ?: ResponseResource(ERROR, message = Error.GENERAL)
      }
  }
}