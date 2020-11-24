package com.noha.moviesadvanced.presentation.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.noha.moviesadvanced.BuildConfig

@BindingAdapter("loadImageUrl")
fun loadImage(imageView: ImageView, url: String?) {

    url?.let {
        Glide.with(imageView)
            .load(BuildConfig.IMAGE_BASE_URL + url)
            .into(imageView)
    }
}