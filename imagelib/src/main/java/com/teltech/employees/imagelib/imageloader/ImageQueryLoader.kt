package com.teltech.employees.imagelib.imageloader

import android.widget.ImageView

interface ImageQueryLoader {

    fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int,
        skipMemoryCache: Boolean = true
    )
}
