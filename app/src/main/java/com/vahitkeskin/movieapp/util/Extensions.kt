package com.vahitkeskin.movieapp.util

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.vahitkeskin.movieapp.R


/**
 * @authot: Vahit Keskin
 * creared on 24.03.2023
 */

infix fun ImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .placeholder(R.drawable.ic_no_photo)
        .transform(CenterCrop(), RoundedCorners(10))
        .into(this)
}

fun View.myVisible() {
    this.visibility = View.VISIBLE
}

fun View.myGone() {
    this.visibility = View.GONE
}

infix fun View.visibleIf(boolean: Boolean) {
    if (boolean) this.myVisible() else this.myGone()
}