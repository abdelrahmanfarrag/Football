package com.example.football.presentation.common

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
sealed class ResourceState {
  object LOADING : ResourceState()
  object SUCCESS : ResourceState()
  object ERROR : ResourceState()
}