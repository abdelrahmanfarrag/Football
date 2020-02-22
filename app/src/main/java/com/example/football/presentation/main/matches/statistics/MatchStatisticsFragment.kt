package com.example.football.presentation.main.matches.statistics

import android.os.Bundle
import com.example.football.R
import com.example.football.data.model.LiveScores.Match
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.main.MainActivity
import com.example.football.utils.extensions.getViewModel
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(R.layout.fragment_match_statistics)
class MatchStatisticsFragment : BaseFragment() {

  @Inject lateinit var factory: ViewModelFactoryProvider
  private lateinit var match: Match

  private val statisticsViewModel by lazy {
    getViewModel(
      MatchStatisticsViewModel::class.java,
      factory
    )
  }
  private var matchId: Int = 0

  override fun setupInjection(component: FragmentSubComponent) {
    component.inject(this)
  }

  override fun afterFragmentInstantiate(savedInstanceState: Bundle?) {
    receiveMatchId()
    (activity as MainActivity).hideBottomNavigationView()
    (activity as MainActivity).setupToolbarTitle(getString(R.string.statistics))
    callMatchStatisticsService()
  }

  override fun onDestroyView() {
    (activity as MainActivity).showBottomNavigationView()
    super.onDestroyView()
  }

  private fun receiveMatchId() {
    val bundleArgs = arguments?.let {
      MatchStatisticsFragmentArgs.fromBundle(it)
    }
    bundleArgs?.let {
      matchId = it.matchId
      match = it.match
    }

  }

  private fun callMatchStatisticsService() {
    statisticsViewModel.id = matchId
    statisticsViewModel.loadMatchStatistics()
  }
}