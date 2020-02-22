package com.example.football.di.presentation

import com.example.football.data.remote.LiveScoreApi
import com.example.football.data.remote.Validator
import com.example.football.di.presentation.scope.PerFragment
import com.example.football.presentation.main.matches.MatchesRepository
import com.example.football.presentation.main.matches.adapter.MatchesAdapter
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Module
class FragmentModule {
  @Provides
  @PerFragment
  fun provideMatchesRepository(
    liveScoreApi: LiveScoreApi,
    validator: Validator
  ) = MatchesRepository(liveScoreApi, validator)

  @Provides
  @PerFragment
  fun provideMatchAdapter() = MatchesAdapter()
}