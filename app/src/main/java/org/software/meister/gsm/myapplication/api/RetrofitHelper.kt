package org.software.meister.gsm.myapplication.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitHelper {
    companion object {

        private val BASE_URL = "https://open.neis.go.kr/hub/"

        fun getInstance(): RetrofitHelper {
            return RetrofitHelper()
        }

        fun getRetrofit(instance : RetrofitHelper) : Retrofit {
            return instance.retrofit
        }

        fun getAPI(rt: Retrofit) : API {
            return rt.create(API::class.java)
        }
    }

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.MINUTES)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}