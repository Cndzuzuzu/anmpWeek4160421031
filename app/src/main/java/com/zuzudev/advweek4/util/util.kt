package com.zuzudev.advweek4.util

import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import com.zuzudev.advweek4.R

fun ImageView.loadImage(url: String?, progressBar:ProgressBar) {
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}
