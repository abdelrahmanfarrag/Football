package com.example.football.di.application

import android.app.Application
import com.example.football.di.application.scope.ApplicationScope
import com.example.football.di.presentation.FragmentSubComponent
import dagger.BindsInstance
import dagger.Component

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@ApplicationScope
@Component(modules = [ApiModule::class])
interface AppComponent {

  fun getFragmentSubComponent(): FragmentSubComponent.Builder

  @Component.Builder
  interface Builder {
    @BindsInstance fun bindApplication(application: Application): Builder
    fun build(): AppComponent
  }
}