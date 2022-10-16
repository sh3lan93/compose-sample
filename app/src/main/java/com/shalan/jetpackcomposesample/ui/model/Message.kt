package com.shalan.jetpackcomposesample.ui.model

import androidx.annotation.DrawableRes
import com.shalan.jetpackcomposesample.R

data class Message(
    val author: String,
    val body: String,
    @DrawableRes val profilePicture: Int = R.drawable.profile_picture
)
