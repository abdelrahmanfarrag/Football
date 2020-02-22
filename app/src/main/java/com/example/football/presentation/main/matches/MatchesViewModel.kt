package com.example.football.presentation.main.matches

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.football.data.model.LiveScores
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
class MatchesViewModel @Inject constructor(private val matchesRepository: MatchesRepository) :
  ViewModel() {

  private val compositeDisposable = CompositeDisposable()
  private val _liveScores = MutableLiveData<ResponseResource<LiveScores>>()

  val liveScores: LiveData<ResponseResource<LiveScores>>
    get() = _liveScores

  fun loadLiveScores() {
    matchesRepository.getLiveMapScore()
      .doOnSubscribe { _liveScores.setLoading() }
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({ liveScoresResource ->
        if (liveScoresResource.responseState == SUCCESS) {
          _liveScores.setSuccess(liveScoresResource.responseData!!)
        } else {
          _liveScores.setError(liveScoresResource.message)
        }
      }, { throwable ->
        if (throwable.message == NetworkInterceptor.NETWORK_ISSUE) {
          _liveScores.setError(Error.NETWORK)
        } else {
          _liveScores.setError(Error.GENERAL)
        }
      })
      .addTo(compositeDisposable)


  }

  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
  }
}