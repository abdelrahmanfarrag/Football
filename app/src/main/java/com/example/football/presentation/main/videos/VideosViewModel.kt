package com.example.football.presentation.main.videos

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.football.data.model.Videos
import com.example.football.data.remote.NetworkInterceptor
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.common.ResponseResource
import com.example.football.utils.Error
import com.example.football.utils.extensions.addTo
import com.example.football.utils.extensions.setError
import com.example.football.utils.extensions.setLoading
import com.example.football.utils.extensions.setSuccess
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class VideosViewModel @Inject constructor(private val videosRepository: VideosRepository) :
  ViewModel() {
  private val compositeDisposable = CompositeDisposable()
  private val _videos = MutableLiveData<ResponseResource<List<Videos>>>()

  val videos: LiveData<ResponseResource<List<Videos>>>
    get() = _videos

  fun loadVideos() {
    videosRepository.getVideos()
      .doOnSubscribe { _videos.setLoading() }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ videosResource ->
        if (videosResource.responseState == SUCCESS) {
          _videos.setSuccess(videosResource.responseData!!)
        } else {
          _videos.setError(videosResource.message)
        }
      }, { throwable ->
        if (throwable.message == NetworkInterceptor.NETWORK_ISSUE) {
          _videos.setError(Error.NETWORK)
        } else {
          Log.d("errorHappens",throwable.toString())
          _videos.setError(Error.GENERAL)
        }
      })
      .addTo(compositeDisposable)
  }

  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
  }

}