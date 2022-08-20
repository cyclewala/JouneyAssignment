package com.learning.journey.base.core.utils

import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import de.hdodenhof.circleimageview.CircleImageView

object GlideUtil {

    fun loadDrawableImage(drawable: Drawable, imageView: AppCompatImageView) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(drawable)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }

    fun loadImageFromUrlWithCache(
        url: String,
        placeHolder: Drawable,
        imageView: AppCompatImageView,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(url).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(placeHolder).into(imageView)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }

    fun loadLocalImageFromUriWithCache(
        uri: Uri,
        placeHolder: Drawable,
        imageView: AppCompatImageView,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(uri).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(placeHolder).into(imageView)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }

    fun loadLocalCircleImageFromUriWithCache(
        uri: Uri,
        placeHolder: Drawable,
        imageView: CircleImageView,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(uri).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .placeholder(placeHolder).into(imageView)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }


    fun loadCustomSizeImageFromUrlWithCache(
        url: String,
        placeHolder: Drawable,
        imageView: AppCompatImageView,
        width: Int,
        height: Int,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(url).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .override(width, height)
                    .placeholder(placeHolder).into(imageView)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }

    fun loadLocalCustomSizeImageFromUriWithCache(
        uri: Uri,
        placeHolder: Drawable,
        imageView: AppCompatImageView,
        width: Int,
        height: Int,
    ) {
        Handler(Looper.getMainLooper()).post {
            try {
                Glide.with(imageView).load(uri).diskCacheStrategy(DiskCacheStrategy.DATA)
                    .override(width, height)
                    .placeholder(placeHolder).into(imageView)
            } catch (e: Exception) {
                Log.e("GlideUtil", e.localizedMessage)
            }
        }
    }

}