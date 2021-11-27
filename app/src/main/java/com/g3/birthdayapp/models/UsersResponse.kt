package com.g3.birthdayapp.models

import com.google.gson.annotations.SerializedName

data class UsersResponse(
    @SerializedName("results") val users: List<User>
)