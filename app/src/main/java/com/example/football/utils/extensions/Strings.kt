package com.example.football.utils.extensions

import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */

fun String.splitWord(char: String) =
  this.split(char)

fun TextView.formatDate(time: String) {
  val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
  val output = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
  @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS") val d: Date = sdf.parse(time)
  this.text = output.format(d)
}

