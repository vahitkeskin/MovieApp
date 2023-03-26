package com.vahitkeskin.movieapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * @authot: Vahit Keskin
 * creared on 26.03.2023
 */

@BindingAdapter("android:movieImage")
fun movieImage(view: ImageView, url: String?) {
    view.loadImage(Contains.IMAGE_URL_BASE_URL + url)
}

@BindingAdapter("android:movieCorrectedDate")
fun movieCorrectedDate(view: TextView, url: String) {
    view.text = url.replace("-",".").dateArrangement()
}