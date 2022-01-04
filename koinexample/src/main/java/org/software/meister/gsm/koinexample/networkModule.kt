package org.software.meister.gsm.koinexample

import com.trello.rxlifecycle3.android.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { GsonConverterFactory.create() as Converter.Factory }
    single { RxJava2CallAdapterFactory.create() as CallAdapter.Factory }
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        };


        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().build()
                chain.proceed(request)
            }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://open.neis.go.kr/hub/")
            .addCallAdapterFactory(get())
            .addConverterFactory(get())
            .client(get())
            .build()
            .create(API::class.java)
    }
}