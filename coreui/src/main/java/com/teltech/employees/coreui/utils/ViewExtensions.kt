package com.teltech.employees.coreui.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

fun View.show(show: Boolean = true) {
    if (visibility == View.VISIBLE && show) return
    if (visibility == View.GONE && !show) return
    visibility = if (show) View.VISIBLE else View.GONE
}

fun View.hide() = show(false)

fun RecyclerView.addCustomDecoration(context: Context, drawableResourceId: Int) {
    addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).apply {
        ContextCompat.getDrawable(context, drawableResourceId)
            ?.let(this::setDrawable)
    })
}
