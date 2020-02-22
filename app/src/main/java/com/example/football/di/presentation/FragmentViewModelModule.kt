package com.example.football.di.presentation

import androidx.lifecycle.ViewModel
import com.example.football.di.presentation.scope.ViewModelKey
import com.example.football.di.presentation.viewmodel.FactoryProviderModule
import com.example.football.presentation.main.matches.MatchesViewModel
import com.example.football.presentation.main.matches.statistics.MatchStatisticsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Suppress("unused")
@Module(includes = [FactoryProviderModule::class])
abstract class FragmentViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MatchesViewModel::class)
  abstract fun bindMatchesViewModel(matchesViewModel: MatchesViewModel): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MatchStatisticsViewModel::class)
  abstract fun bindMatchStatisticsViewModel(matchesViewModel: MatchStatisticsViewModel): ViewModel


}