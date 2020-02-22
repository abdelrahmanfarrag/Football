package com.example.football.di.presentation.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Module
abstract class FactoryProviderModule {
  @Binds
  abstract fun bindsViewModelFactory(viewModelFactoryProvider: ViewModelFactoryProvider): ViewModelProvider.Factory
}