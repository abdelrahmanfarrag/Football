package com.example.football.utils.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */

fun Context.toast(msg: String?) {
  msg?.let {
    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
  }
}

fun Context.toastLong(msg: String?) {
  msg?.let {
    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
  }
}

@Suppress("DEPRECATION") fun Context.isNetworkAvailable(): Boolean {
  val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    val nw = connectivityManager.activeNetwork ?: return false
    val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
    return when {
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
      actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
      else -> false
    }
  } else {
    val nwInfo = connectivityManager.activeNetworkInfo ?: return false
    return nwInfo.isConnected
  }
}

fun Context.getAppDrawable(
  @DrawableRes idRes: Int
): Drawable? = ContextCompat.getDrawable(this, idRes)

fun Context.getAppColor(
  @ColorRes idRes: Int
): Int = ContextCompat.getColor(this, idRes)
