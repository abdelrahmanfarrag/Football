package com.example.football.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.football.R.id
import com.example.football.R.layout
import com.example.football.utils.extensions.gone
import com.example.football.utils.extensions.visible
import kotlinx.android.synthetic.main.activity_main.mainBottomNavigation
import kotlinx.android.synthetic.main.activity_main.mainToolbar
import kotlinx.android.synthetic.main.activity_main.toolbarTitleTextView

class MainActivity : AppCompatActivity() {

  private lateinit var navController: NavController

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)
  }

  override fun onResume() {
    super.onResume()
    toolbarSetup()
    setupNavigationComponent()
  }

  private fun toolbarSetup() {
    setSupportActionBar(mainToolbar)
    supportActionBar?.setDisplayShowTitleEnabled(false)
  }

  private fun setupNavigationComponent() {
    navController = Navigation.findNavController(this, id.navigationComponentContainer)
    val appBarConfiguration = AppBarConfiguration(setOf(id.tabMatches, id.tabVideos))
    mainBottomNavigation.setupWithNavController(navController)
    setupActionBarWithNavController(navController, appBarConfiguration)
    NavigationUI.setupActionBarWithNavController(this, navController)
  }

  override fun onSupportNavigateUp(): Boolean {
    return NavigationUI.navigateUp(navController, null)
  }

  fun setupToolbarTitle(title: String) {
    toolbarTitleTextView.text = title
  }

  fun hideBottomNavigationView() {
    mainBottomNavigation.gone()
  }

  fun showBottomNavigationView() {
    mainBottomNavigation.visible()
  }
}
