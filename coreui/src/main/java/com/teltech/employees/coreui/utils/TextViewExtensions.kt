package com.teltech.employees.coreui.utils

import android.widget.TextView
import androidx.annotation.DrawableRes


/**
 * Wrapper around setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom) which sets only the left drawable and removes others
 */
fun TextView.setLeftDrawable(@DrawableRes drawableRes: Int) = setCompoundDrawablesWithIntrinsicBounds(drawableRes, 0, 0, 0)

/**
 * Wrapper around setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom) which sets every drawable to 0
 */
fun TextView.removeDrawables() = setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
