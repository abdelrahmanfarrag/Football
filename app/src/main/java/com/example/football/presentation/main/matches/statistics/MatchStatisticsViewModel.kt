package com.example.football.presentation.main.matches.statistics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.football.data.model.MatchStatistics
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
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class MatchStatisticsViewModel @Inject constructor(private val matchStatisticsRepository: MatchStatisticsRepository) :
  ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  private val _statistics = MutableLiveData<ResponseResource<MatchStatistics>>()

  val statistics: LiveData<ResponseResource<MatchStatistics>>
    get() = _statistics

  var id =0

  fun loadMatchStatistics() {
    matchStatisticsRepository.loadMatchStatistics(id)
      .doOnSubscribe { _statistics.setLoading() }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ liveScoresResource ->
        if (liveScoresResource.responseState == SUCCESS) {
          _statistics.setSuccess(liveScoresResource.responseData!!)
        } else {
          _statistics.setError(liveScoresResource.message)
        }
      }, { throwable ->
        if (throwable.message == NetworkInterceptor.NETWORK_ISSUE) {
          _statistics.setError(Error.NETWORK)
        } else {
          Log.d("errorHappens",throwable.toString())
          _statistics.setError(Error.GENERAL)
        }
      })
      .addTo(compositeDisposable)
  }

  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
  }
}