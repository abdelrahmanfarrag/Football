package com.example.football.utils

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * Authored by Abdelrahman Ahmed on 23 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
class CustomLayoutManager(context: Context) : LinearLayoutManager(context) {

   var isScrolling:Boolean = true

  fun setScrollEnabled(flag: Boolean) {
    this.isScrolling = flag
  }

  override fun canScrollVertically(): Boolean {
    return isScrolling && super.canScrollVertically()
  }
}