package com.example.football.di.presentation

import com.example.football.di.presentation.scope.PerFragment
import com.example.football.presentation.base.BaseFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class, FragmentViewModelModule::class])
interface FragmentSubComponent {

  @Subcomponent.Builder
  interface Builder {

    @BindsInstance fun bindsFragmentContext(fragment: BaseFragment): Builder

    fun build(): FragmentSubComponent
  }
}