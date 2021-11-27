package com.g3.birthdayapp.api

import com.g3.birthdayapp.models.UsersResponse
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_SEED = "chalkboard"

interface RandomUserApi {

    @GET("api")
    suspend fun getRandomUsers(@Query("results") usersCount: Int, @Query("inc") inc: String, @Query("seed") seed: String = API_SEED): UsersResponse
}