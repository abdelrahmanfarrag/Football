package com.example.football.presentation.base

import androidx.annotation.LayoutRes
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Authored by Abdelrahman Ahmed on 22 Feb, 2020.
 * Contact: abdelrahmanfarrag291@gmail.com
 */

@Retention(RUNTIME)
@Target(CLASS)
annotation class Layout(@LayoutRes val value: Int)