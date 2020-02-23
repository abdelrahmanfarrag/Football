package com.example.football.presentation.main.videos

import android.os.Bundle
import com.example.football.R
import com.example.football.R.layout
import com.example.football.R.string
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.presentation.main.MainActivity
import com.example.football.utils.extensions.clearBackStack
import com.example.football.utils.extensions.setFragmentTitle

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(layout.fragment_videos)
class VideosFragment : BaseFragment() {
  override fun afterFragmentInstantiate(savedInstanceState: Bundle?) {
    (context as MainActivity).supportActionBar?.setHomeAsUpIndicator(null)
    (context as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
    clearBackStack()
    setFragmentTitle(getString(string.videos))
  }

}