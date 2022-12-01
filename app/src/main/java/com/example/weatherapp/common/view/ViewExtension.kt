package com.example.weatherapp.common.view

import android.view.View
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.core.view.isVisible

fun View.visible() {
    isVisible = true
}

fun View.invisible() {
    isInvisible = true
}

fun View.gone() {
    isGone = true
}

/**
 * Change the visibility of a view.
 * @param visible True -> Visible or False -> Invisible
 */
fun View.isVisibleOrInvisible(visible: Boolean) {
    visibility = if (visible)
        View.VISIBLE
    else
        View.INVISIBLE
}

/**
 * Change the visibility of a view.
 * @param visible True -> Visible or False -> Gone
 */
fun View.isVisibleOrGone(visible: Boolean) {
    visibility = if (visible)
        View.VISIBLE
    else
        View.GONE
}