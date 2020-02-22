package com.example.football.utils.extensions

import androidx.lifecycle.MutableLiveData
import com.example.football.presentation.common.ResourceState.ERROR
import com.example.football.presentation.common.ResourceState.LOADING
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.common.ResponseResource

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
fun <T> MutableLiveData<ResponseResource<T>>.setSuccess(
  data: T
) = postValue(ResponseResource(SUCCESS, data))

fun <T> MutableLiveData<ResponseResource<T>>.setLoading(
) = postValue(ResponseResource(LOADING, value?.responseData))

fun <T> MutableLiveData<ResponseResource<T>>.setError(
  message: String
) = postValue(ResponseResource(ERROR, value?.responseData, message))
