package com.teltech.employees.imagelib.imageloader

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.teltech.employees.imagelib.R

class ImageQueryLoaderImpl(
    private val context: Context
) : ImageQueryLoader {

    override fun loadWithKey(
        target: ImageView,
        key: String,
        errorResourceId: Int,
        skipMemoryCache: Boolean
    ) {
     /*   val placeholder =
            circularProgressDrawableFactory.createProgressDrawable(context.resources.getDimension(R.dimen.imagelib_circular_drawable_radius))
        clearImage(target)

        showImage(
            key,
            target,
            placeholder,
            ContextCompat.getDrawable(context, errorResourceId),
            skipMemoryCache
        )*/
    }

    private fun showImage(
        image: Any,
        target: ImageView,
        placeholder: Drawable,
        errorPlaceholder: Drawable?,
        skipMemoryCache: Boolean
    ) {
        Glide.with(context)
            .applyDefaultRequestOptions(
                RequestOptions()
                    .format(DecodeFormat.PREFER_ARGB_8888)
                    .placeholder(placeholder)
                    .error(errorPlaceholder)
            )
            .asBitmap()
            .load(image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(skipMemoryCache)
            .into(target)
    }

    private fun clearImage(target: ImageView) {
        Glide.with(context).clear(target)
    }
}
