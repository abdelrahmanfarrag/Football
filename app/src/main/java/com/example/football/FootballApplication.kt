package com.example.football

import android.app.Application
import androidx.fragment.app.FragmentActivity
import com.example.football.di.application.AppComponent
import com.example.football.di.application.DaggerAppComponent

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class FootballApplication : Application() {

  companion object {
    fun get(activity: FragmentActivity) = activity.application as FootballApplication
  }

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    setupInjection()
  }

  private fun setupInjection() {
    appComponent = DaggerAppComponent.builder()
      .bindApplication(this)
      .build()
  }


}