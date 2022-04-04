package com.bibliotecadebolso.app.data.repository

import com.bibliotecadebolso.app.data.api.LoginAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BibliotecaDeBolsoRepository {

    private const val BASE_URL = "https://bibliotecadebolso.herokuapp.com/api/"

    private val headerBodyInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient.Builder()
            .apply {
                addInterceptor(interceptor = headerBodyInterceptor)
            }
            .build()


    fun retrofit(buildApi: String? = null): LoginAPI = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(LoginAPI::class.java)
}