package com.example.football.di.application

import com.example.football.data.Constants.Urls.LIVE_SCORES_BASE_URL
import com.example.football.data.Constants.Urls.SCORE_BAT_BASE_URL
import com.example.football.di.application.qualifier.LiveScores
import com.example.football.di.application.qualifier.ScoreBat
import com.example.football.di.application.scope.ApplicationScope
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Module(includes = [OkHttpModule::class])
class NetworkModule {

  @Provides
  @LiveScores
  @ApplicationScope fun providesLiveScoreRetrofitInstance(
    client: OkHttpClient
    , gson: Gson
  ): Retrofit = Retrofit.Builder()
    .baseUrl(LIVE_SCORES_BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

  @Provides
  @ScoreBat
  @ApplicationScope fun provideScoreBatRetrofitInstance(
    client: OkHttpClient,
    gson: Gson
  ): Retrofit = Retrofit.Builder()
    .baseUrl(SCORE_BAT_BASE_URL)
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

}