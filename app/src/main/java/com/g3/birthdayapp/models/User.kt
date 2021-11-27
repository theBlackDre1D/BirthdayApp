package com.g3.birthdayapp.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("name") val name: Name,
    @SerializedName("dob") val dob: Dob,
    @SerializedName("picture") val picture: Picture?
) : Serializable