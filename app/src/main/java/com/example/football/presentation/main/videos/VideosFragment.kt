package com.example.football.presentation.main.videos

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.football.R.layout
import com.example.football.R.string
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.common.ResourceState
import com.example.football.presentation.main.MainActivity
import com.example.football.presentation.main.videos.adapter.VideosAdapter
import com.example.football.utils.Error
import com.example.football.utils.extensions.clearBackStack
import com.example.football.utils.extensions.endlessScrolling
import com.example.football.utils.extensions.getViewModel
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.setFragmentTitle
import com.example.football.utils.extensions.toast
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.fragment_videos.videosLoadingFrame
import kotlinx.android.synthetic.main.fragment_videos.videosRecycler
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(layout.fragment_videos)
class VideosFragment : BaseFragment() {

  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var adapter: VideosAdapter
  private val videosViewModel by lazy { getViewModel(VideosViewModel::class.java, factoryProvider) }

  private var lastIndexItem = 0

  override fun setupInjection(component: FragmentSubComponent) {
    component.inject(this)
  }

  override fun afterFragmentInstantiate(savedInstanceState: Bundle?) {
    (context as MainActivity).supportActionBar?.setHomeAsUpIndicator(null)
    (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    clearBackStack()
    setFragmentTitle(getString(string.videos))
    callVideosWebService()
    observeVideoResponse()
  }

  private fun callVideosWebService() {
    videosViewModel.loadVideos()
  }

  private fun observeVideoResponse() {
    videosViewModel.videos.observe(viewLifecycleOwner, Observer { response ->
      when (response.responseState) {
        ResourceState.LOADING -> videosLoadingFrame.visible()
        ResourceState.SUCCESS -> {
          response.responseData?.let { videos ->

            adapter.setData(videos)
            videosRecycler.putMediaObject(videos.toMutableList())
            videosRecycler.adapter = adapter
            lastIndexItem = adapter.itemCount - videos.size
            videosRecycler.getCustomManager().scrollToPosition(lastIndexItem)
            videosRecycler.endlessScrolling(videosRecycler.getCustomManager()) { callVideosWebService() }

          }
          videosLoadingFrame.gone()

        }
        ResourceState.ERROR -> {
          videosLoadingFrame.gone()
          when (response.message) {
            Error.GENERAL -> toast(getString(string.error_occurred))
            Error.NETWORK -> toast(getString(string.network_error))
            else -> toast(getString(string.error_occurred))
          }
        }
      }

    })
  }

  override fun onDestroyView() {
    super.onDestroyView()
    adapter.clear()
    adapter.notifyDataSetChanged()
    lastIndexItem = 0
  }

}