package com.example.football.di.application

import com.example.football.data.remote.LiveScoreApi
import com.example.football.data.remote.ScoreBatApi
import com.example.football.di.application.qualifier.LiveScores
import com.example.football.di.application.qualifier.ScoreBat
import com.example.football.di.application.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Module(includes = [NetworkModule::class])
class ApiModule {

  @Provides
  @ApplicationScope fun provideScoreBatApi(
    @ScoreBat retrofit: Retrofit
  ): ScoreBatApi = retrofit.create(ScoreBatApi::class.java)

  @Provides
  @ApplicationScope fun provideLiveScoresApi(
    @LiveScores retrofit: Retrofit
  ): LiveScoreApi = retrofit.create(LiveScoreApi::class.java)
}