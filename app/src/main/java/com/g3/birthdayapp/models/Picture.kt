package com.g3.birthdayapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Picture(
    @SerializedName("thumbnail") val thumbnail: String?
) : Serializable