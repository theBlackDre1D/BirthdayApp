package com.g3.birthdayapp.models

import com.google.gson.annotations.SerializedName

data class Picture(
    @SerializedName("thumbnail") val thumbnail: String
)