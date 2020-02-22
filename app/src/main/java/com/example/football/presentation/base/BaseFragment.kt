package com.example.football.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.football.FootballApplication
import com.example.football.R
import com.example.football.di.presentation.FragmentSubComponent
import com.example.football.utils.extensions.getAppColor
import java.lang.UnsupportedOperationException

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
abstract class BaseFragment : Fragment() {


  private val fragmentSubComponent: FragmentSubComponent by lazy {
    FootballApplication.get(activity!!).appComponent.getFragmentSubComponent().bindsFragmentContext(this).build()
  }
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    getLayoutResource()?.let { layoutResource ->
      val view = inflater.inflate(layoutResource, container, false)
      view.setBackgroundColor(activity!!.getAppColor(R.color.white))
      view.isClickable = true
      view.isFocusable = true
      return view
    } ?: throw UnsupportedOperationException("You must call @Layout(res) annotation above fragment")
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    setupInjection(fragmentSubComponent)
    super.onViewCreated(view, savedInstanceState)
    afterFragmentInstantiate(view, savedInstanceState)

  }

  private fun getLayoutResource(): Int? = javaClass.getAnnotation(Layout::class.java)?.value

  protected abstract fun afterFragmentInstantiate(view: View, savedInstanceState: Bundle?)
  protected open fun setupInjection(component: FragmentSubComponent) {}

}