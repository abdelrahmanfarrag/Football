package com.example.football.di.application

import android.app.Application
import android.util.Log
import com.example.football.BuildConfig
import com.example.football.data.remote.NetworkInterceptor
import com.example.football.di.application.scope.ApplicationScope
import com.example.football.utils.extensions.isNetworkAvailable
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor.Chain
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Module
class OkHttpModule {

  @Provides
  @ApplicationScope
  fun providesHttpClientToRetrofit(
    loggingInterceptor: HttpLoggingInterceptor,
    networkInterceptor: NetworkInterceptor

  ): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(loggingInterceptor)
      .addInterceptor(networkInterceptor)
      .connectTimeout(30, TimeUnit.SECONDS)
      .readTimeout(30, TimeUnit.SECONDS)
      .writeTimeout(30, TimeUnit.SECONDS)
      .addInterceptor { interceptSocketException(it) }
      .retryOnConnectionFailure(true)
      .build()
  }

  @Provides
  @ApplicationScope fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    return logging
  }

  @Provides
  @ApplicationScope fun getNetworkInterceptor(application: Application): NetworkInterceptor =
    object : NetworkInterceptor() {
      override fun isInternetAvailable(): Boolean = application.isNetworkAvailable()
    }

  @Throws(IOException::class) private fun interceptSocketException(chain: Chain): Response {
    val response = chain.proceed(chain.request())
    try {
      return response.newBuilder()
        .body(response.body?.string()?.toResponseBody(response.body?.contentType()))
        .build()
    } catch (exception: SocketTimeoutException) {
      Log.d("exception", exception.toString())
    }
    return response
  }

}