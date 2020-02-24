package com.example.football.presentation.main.matches

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.football.R.layout
import com.example.football.R.string
import com.example.football.data.model.LiveScores.Match
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.common.ResourceState
import com.example.football.presentation.main.matches.adapter.MatchesAdapter
import com.example.football.utils.Error
import com.example.football.utils.extensions.endlessScrolling
import com.example.football.utils.extensions.getViewModel
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.setFragmentTitle
import com.example.football.utils.extensions.toast
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_matches.liveScoresMatchesList
import kotlinx.android.synthetic.main.fragment_matches.loadingFrame
import kotlinx.android.synthetic.main.fragment_matches.noAvailableMatchesTextView
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(layout.fragment_matches)
class MatchesFragment : BaseFragment() {

  @Inject lateinit var factory: ViewModelFactoryProvider
  @Inject lateinit var adapter: MatchesAdapter
  private lateinit var layoutManager: LinearLayoutManager
  private var lastIndexItem = 0

  private val matchesViewModel by lazy { getViewModel(MatchesViewModel::class.java, factory) }

  override fun setupInjection(component: FragmentSubComponent) {
    component.inject(this)
  }

  override fun afterFragmentInstantiate(savedInstanceState: Bundle?) {
    setFragmentTitle(getString(string.matches))
    layoutManager = LinearLayoutManager(this.activity)
    retainInstance = true
    callLiveScoresService()
    observeLiveScoresResponse()
  }

  private fun callLiveScoresService() {
    matchesViewModel.loadLiveScores()
  }

  private fun observeLiveScoresResponse() {
    matchesViewModel.liveScores.observe(viewLifecycleOwner, Observer { liveScores ->
      when (liveScores.responseState) {
        ResourceState.LOADING -> loadingFrame.visible()
        ResourceState.SUCCESS -> {
          loadingFrame.gone()
          liveScores.responseData?.let { data ->
            adapter.setMatchClickListener { id, itemView, match ->
              toStatisticsFragment(id, match, itemView)
            }
            if (data.data.matches.isNotEmpty()) {
              noAvailableMatchesTextView.gone()
              liveScoresMatchesList.visible()

              adapter.setData(data.data.matches)
              liveScoresMatchesList.layoutManager = layoutManager
              liveScoresMatchesList.adapter = adapter
              lastIndexItem = adapter.itemCount - data.data.matches.size
              layoutManager.scrollToPosition(lastIndexItem)
              liveScoresMatchesList.endlessScrolling(layoutManager) { callLiveScoresService() }
            }else{
              noAvailableMatchesTextView.visible()
              liveScoresMatchesList.gone()
            }
          }
        }
        ResourceState.ERROR -> {
          loadingFrame.gone()
          when (liveScores.message) {
            Error.GENERAL -> toast(getString(string.error_occurred))
            Error.NETWORK -> toast(getString(string.network_error))
            else -> toast(getString(string.error_occurred))
          }
        }
      }
    })
  }

  private fun toStatisticsFragment(id: Int, match: Match, view: View) {
    adapter.clear()
    adapter.notifyDataSetChanged()
    lastIndexItem = 0
    val action = MatchesFragmentDirections.actionToMatchStatisticsFragment(id, match)
    Navigation.findNavController(view).navigate(action)
  }



  override fun onDestroyView() {
    super.onDestroyView()
    adapter.clear()
    adapter.notifyDataSetChanged()
    lastIndexItem = 0
  }
}