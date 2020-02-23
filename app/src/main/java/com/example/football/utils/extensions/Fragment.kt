package com.example.football.utils.extensions

import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.football.presentation.base.BaseFragment
import com.example.football.presentation.main.MainActivity

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
fun <T : ViewModel> BaseFragment.getViewModel(
  modelClass: Class<T>, viewModelFactory: ViewModelProvider.Factory? = null
): T {
  return viewModelFactory?.let {
    ViewModelProvider(this, it).get(modelClass)
  } ?: ViewModelProvider(this).get(modelClass)
}

fun BaseFragment.getColor(
  @ColorRes idRes: Int
): Int? = activity?.let { ContextCompat.getColor(it, idRes) }

fun BaseFragment.closeFragment() {
  activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
}

fun BaseFragment.clearBackStack() {
  activity?.supportFragmentManager?.let {
    it.apply { if (backStackEntryCount > 0) for (i in 0..backStackEntryCount) popBackStackImmediate() }
  }
}

fun BaseFragment.toast(msg: String?) {
  msg?.let {
    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
  }
}
fun BaseFragment.setFragmentTitle(title: String) {
  if (this.activity is MainActivity) {
    (activity as MainActivity).setupToolbarTitle(title)
  }
}


