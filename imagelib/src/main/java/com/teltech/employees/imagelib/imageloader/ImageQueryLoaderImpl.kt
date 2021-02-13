package com.teltech.employees.imagelib.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.teltech.employees.coreui.utils.CircularProgressDrawableFactory
import com.teltech.employees.imagelib.BuildConfig
import com.teltech.employees.imagelib.R

class ImageQueryLoaderImpl(
    private val context: Context,
    private val circularProgressDrawableFactory: CircularProgressDrawableFactory
) : ImageQueryLoader {

    override fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int
    ) {
        val placeholder =
            circularProgressDrawableFactory.createProgressDrawable(context.resources.getDimension(R.dimen.imagelib_circular_drawable_radius))
        clearImage(target)

        showImage(
            key,
            target,
            placeholder,
            ContextCompat.getDrawable(context, errorResourceId),
        )
    }

    private fun showImage(
        image: String,
        target: ImageView,
        placeholder: Drawable,
        errorPlaceholder: Drawable?
    ) {
        Glide.with(context)
            .applyDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
            )
            .load(image)
            .skipMemoryCache(true)
            .into(target)
    }

    private fun clearImage(target: ImageView) {
        Glide.with(context).clear(target)
    }
}
