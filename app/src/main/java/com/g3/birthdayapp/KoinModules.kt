package com.g3.birthdayapp

import com.g3.birthdayapp.api.RandomUserApi
import com.g3.birthdayapp.repositories.UsersRepository
import com.g3.birthdayapp.screens.usersList.UsersListFragmentViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModules = module {

    // ViewModels
    viewModel { UsersListFragmentViewModel( get() ) }

    // Repositories
    single { UsersRepository( get() ) }

    // Retrofit
    factory { provideOkHttpClient() }
    factory { provideRandomUserApi( get() ) }
    single { provideRetrofit( get() ) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient().newBuilder()
//    builder.addInterceptor { chain ->
//        val request = chain.request().newBuilder()
//        val originalHttpUrl = chain.request().url()
//        val url = originalHttpUrl.newBuilder()
//            .addQueryParameter("api_key", "d798f98f28bab699ad468aff361876f9").build()
//        request.url(url)
//        return@addInterceptor chain.proceed(request.build())
//    }
    return builder.build()
}

fun provideRandomUserApi(retrofit: Retrofit): RandomUserApi = retrofit.create(RandomUserApi::class.java)