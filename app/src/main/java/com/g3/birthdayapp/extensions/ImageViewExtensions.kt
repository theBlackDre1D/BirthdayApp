package com.g3.birthdayapp.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url).
        into(this)
}