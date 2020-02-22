package com.example.football.presentation.main.matches

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.football.R.layout
import com.example.football.R.string
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.common.ResourceState
import com.example.football.utils.Error
import com.example.football.utils.extensions.getViewModel
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.setFragmentTitle
import com.example.football.utils.extensions.toast
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_matches.loadingFrame
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(layout.fragment_matches)
class MatchesFragment : BaseFragment() {

  @Inject lateinit var factory: ViewModelFactoryProvider
  private val matchesViewModel by lazy { getViewModel(MatchesViewModel::class.java, factory) }

  override fun setupInjection(component: FragmentSubComponent) {
    component.inject(this)
  }

  override fun afterFragmentInstantiate(view: View, savedInstanceState: Bundle?) {
    setFragmentTitle(getString(string.matches))
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
          }
        }
        ResourceState.ERROR ->
          loadingFrame.gone()
        ResourceState.ERROR -> {
          loadingFrame.gone()
          when (liveScores.message) {
            Error.GENERAL -> toast("something went wrong")
            Error.NETWORK -> toast(("check network connection"))
            else -> toast("something went wrong")
          }
        }
      }
    })
  }

}