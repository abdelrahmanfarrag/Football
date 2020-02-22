package com.example.football.presentation.main.videos

import android.os.Bundle
import com.example.football.R.layout
import com.example.football.R.string
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.base.Layout
import com.example.football.utils.extensions.setFragmentTitle

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
@Layout(layout.fragment_videos)
class VideosFragment : BaseFragment() {
  override fun afterFragmentInstantiate (savedInstanceState: Bundle?) {
    setFragmentTitle(getString(string.videos))
  }

}