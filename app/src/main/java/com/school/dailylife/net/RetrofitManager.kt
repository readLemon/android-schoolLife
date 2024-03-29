package com.mredrock.runtogether.network

import com.school.dailylife.config.Api
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by chenyang
 * on 20-11-22
 */
class RetrofitManager private constructor() {

    companion object {

        private const val DEFAULT_TIME_OUT = 100L
        private const val DEFAULT_READ_TIME_OUT = 100L

        fun getInstance(): RetrofitManager {
            return SingletonHolder.INSTANCE
        }
    }

    private val mRetrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC


        val okHttpClient = with(OkHttpClient.Builder()) {
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(logging)
            build()
        }

        mRetrofit = with(Retrofit.Builder()) {
            client(okHttpClient)
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(Api.BASE_URL)
            build()
        }

    }

    private object SingletonHolder {
        val INSTANCE = RetrofitManager()
    }

    fun <T> create(service: Class<T>): T {
        return mRetrofit.create(service)
    }
}