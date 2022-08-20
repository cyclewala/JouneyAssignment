package com.learning.journey.posts.view.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.learning.journey.R
import com.learning.journey.base.core.utils.GlideUtil

object PostBindingAdapter {

    @JvmStatic
    @BindingAdapter("postThumbnail", requireAll = false)
    fun showPostThumbnail(imageView: AppCompatImageView, url: String?) {
        url?.let {
            val placeHolderDrawable =
                ContextCompat.getDrawable(imageView.context, R.drawable.ic_transparent)

            if (imageView.drawable == null) {
                Glide.with(imageView).clear(imageView)
                imageView.setImageDrawable(null)
            }
            placeHolderDrawable?.let { GlideUtil.loadImageFromUrlWithCache(url, it, imageView) }
        } ?: run {
            Glide.with(imageView).clear(imageView)
            imageView.setImageDrawable(null)
        }
    }
}