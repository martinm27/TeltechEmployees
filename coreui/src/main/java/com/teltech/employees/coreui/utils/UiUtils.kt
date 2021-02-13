package com.teltech.employees.coreui.utils

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.ViewOutlineProvider
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.teltech.employees.coreui.R

open class DiffUtilCallback<T : ListItem> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.id == newItem.id

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

open class ListItem(open val id: Any? = null)

class CircularProgressDrawableFactory(
    private val context: Context,
    private val defaultRadius: Float
) {

    fun createProgressDrawable(radius: Float = defaultRadius): CircularProgressDrawable =
        CircularProgressDrawable(context).apply {
            centerRadius = radius
            start()
        }
}
