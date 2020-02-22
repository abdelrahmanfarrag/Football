package com.example.football.utils.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation.INFINITE
import android.view.animation.Animation.REVERSE
import android.view.animation.AnimationSet
import android.view.animation.DecelerateInterpolator


/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */
fun ViewGroup.inflate(
  layoutId: Int,
  attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun View.visible() {
  visibility = View.VISIBLE
}

fun View.invisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun View.fadeAnimation() {
  val fadeIn = AlphaAnimation(0f, 1f)
  fadeIn.interpolator = DecelerateInterpolator()
  fadeIn.duration = 1000
  fadeIn.repeatCount = INFINITE

  val fadeOut = AlphaAnimation(1f, 0f)
  fadeOut.interpolator = DecelerateInterpolator()
  fadeOut.startOffset = 100
  fadeOut.duration = 1000
  fadeOut.repeatCount = INFINITE

  val animation = AnimationSet(false)
  animation.addAnimation(fadeIn)
  animation.addAnimation(fadeOut)
  this.animation = animation

}