package com.teltech.employees.coreui.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
private const val VIEW_HEIGHT_CHANGE_ANIMATION_DURATION = 300L
private const val HEIGHT_INVISIBLE = 0

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

fun View.animateViewHeight(from: Int, to: Int, duration: Long = VIEW_HEIGHT_CHANGE_ANIMATION_DURATION, onEnd: (() -> Unit)? = null) {
    ValueAnimator.ofInt(from, to).run {
        addUpdateListener {
            layoutParams = layoutParams.apply {
                height = it.animatedValue as Int
            }
        }

        onEnd?.let {
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) = it()
            })
        }

        this.duration = duration
        start()
    }
}

fun View.expandViewHeight() {
    (parent as View).let {
        val widthSpec = View.MeasureSpec.makeMeasureSpec(it.width, View.MeasureSpec.EXACTLY)
        val heightSpec = View.MeasureSpec.makeMeasureSpec(it.height, View.MeasureSpec.UNSPECIFIED)
        measure(widthSpec, heightSpec)
    }
    animateViewHeight(HEIGHT_INVISIBLE, measuredHeight)
}

fun View.collapseViewHeight() {
    animateViewHeight(height, HEIGHT_INVISIBLE)
}

