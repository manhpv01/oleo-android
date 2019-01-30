package com.framgia.oleo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.framgia.oleo.R

class BindingUtils {

    companion object {
        @BindingAdapter("android:imageUrl")
        @JvmStatic
        fun setImageUrl(image: ImageView, uri: String?) {
            Glide.with(image.context).load(uri)
                .apply(RequestOptions().circleCrop().placeholder(R.drawable.no_profile))
                .into(image)
        }
    }
}
