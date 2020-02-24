package com.example.football.presentation.main.matches.statistics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.football.R
import com.example.football.R.string
import com.example.football.data.model.LiveScores.Match
import com.example.football.data.model.MatchStatistics.Statistics
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.common.ResourceState.ERROR
import com.example.football.presentation.common.ResourceState.LOADING
import com.example.football.presentation.common.ResourceState.SUCCESS
import com.example.football.presentation.main.MainActivity
import com.example.football.presentation.main.matches.statistics.adapter.StatisticsAdapter
import com.example.football.utils.Error
import com.example.football.utils.buildStatisticsList
import com.example.football.utils.extensions.getViewModel
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.toast
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsAwayName
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsAwayScore
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsHomeName
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsHomeScore
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsMatchLeague
import kotlinx.android.synthetic.main.fragment_match_statistics.matchStatisticsMatchState
import kotlinx.android.synthetic.main.fragment_match_statistics.statisticsList
import kotlinx.android.synthetic.main.fragment_match_statistics.statisticsLoadingFrame
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(R.layout.fragment_match_statistics)
class MatchStatisticsFragment : BaseFragment() {

  @Inject lateinit var factory: ViewModelFactoryProvider
  @Inject lateinit var statisticsAdapter: StatisticsAdapter
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
    (activity as MainActivity).setupToolbarTitle(getString(string.statistics))
    callMatchStatisticsService()
    observeMatchStatisticsResponse()
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

  private fun observeMatchStatisticsResponse() {
    statisticsViewModel.statistics.observe(viewLifecycleOwner, Observer { statistics ->
      when (statistics.responseState) {
        LOADING -> statisticsLoadingFrame.visible()
        SUCCESS -> {
          statisticsLoadingFrame.gone()
          statistics.responseData?.let { data ->
            bindUI(data.statistics)
          }
        }
        ERROR -> {
          statisticsLoadingFrame.gone()
          when (statistics.message) {
            Error.GENERAL -> toast(getString(string.error_occurred))
            Error.NETWORK -> toast(getString(string.network_error))
            else -> toast(getString(string.error_occurred))
          }
        }
      }
    })

  }

  @SuppressLint("SetTextI18n")
  private fun bindUI(statistics: Statistics) {
    matchStatisticsMatchLeague.text = match.competitionName
    matchStatisticsMatchState.text = "${match.time} '"
    matchStatisticsHomeName.text = match.homeName
    matchStatisticsHomeScore.text = match.score.split("-")[0]
    matchStatisticsAwayName.text = match.awayName
    matchStatisticsAwayScore.text = match.score.split("-")[1]
    statisticsAdapter.setData(buildStatisticsList(statistics, getStatisticsListStrings()))
    statisticsList.adapter = statisticsAdapter
  }

  private fun getStatisticsListStrings() = mutableListOf<String>().apply {
    this.add(getString(string.attacks))
    this.add(getString(string.attempts))
    this.add(getString(string.corners))
    this.add(getString(string.fouls))
    this.add(getString(string.free_kicks))
    this.add(getString(string.offsides))
    this.add(getString(string.possession))
    this.add(getString(string.yellow_cards))
    this.add(getString(string.red_cards))
    this.add(getString(string.shots_on_target))
    this.add(getString(string.shots_off_target))
  }
}
