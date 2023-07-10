package com.example.firebaselogin.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiDetail {
    const val BASE_URL="https://61e947967bc0550017bc61bf.mockapi.io/api/"
    const val END_POINT="v1/people"

//    const val ROOM_URL="https://61e947967bc0550017bc61bf.mockapi.io/api/"
    const val ROOM_URL="v1/rooms"

    //rooms api

    val okHttpClient=OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level= HttpLoggingInterceptor.Level.BODY
        })
        .build()

    val retrofitinstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    val apiClient = retrofitinstance.create(ApiCall::class.java)
}