package com.example.football.presentation.common

import com.example.football.utils.Error

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class ResponseResource<out T> constructor(
  val responseState: ResourceState,
  val responseData: T? = null,
  val message: String = Error.GENERAL
)