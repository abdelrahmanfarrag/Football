package com.example.football.utils.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */

fun ImageView.load(url: String) {
  Glide.with(context)
    .load(url)
    .into(this)
}
fun ImageView.setDrawable(res:Int){
  this.setImageResource(res)
}
