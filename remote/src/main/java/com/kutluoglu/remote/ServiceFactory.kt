package com.kutluoglu.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by F.K. on 2019-06-17.
 *
 */

/**
 * Provide "get" methods to create instances of [FoursquareApi]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */

object ServiceFactory {
    private const val BASE_URL = "https://api.foursquare.com/v2/"

    fun getFoursquareApi(isDebug: Boolean) : FoursquareApi {
        val okHttpClient = getOkHttpClient(
            getLoggingIntercepter(isDebug)
        )

        return getFoursquareApi(okHttpClient, getGson())
    }

    private fun getFoursquareApi(okHttpClient: OkHttpClient, gson: Gson): FoursquareApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(FoursquareApi::class.java)
    }

    private fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create()
    }

    private fun getLoggingIntercepter(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return logging
    }
}