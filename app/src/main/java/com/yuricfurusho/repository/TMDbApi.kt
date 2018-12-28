package com.yuricfurusho.repository

import android.util.Log
import com.yuricfurusho.model.UpcomingResult
import com.yuricfurusho.tmdbapi.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yuri Furusho on 02/11/18.
 */
interface TMDbApi {

    @GET("3/movie/upcoming")
    fun listUpcoming(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Observable<UpcomingResult>

    companion object {

        fun create(): TMDbApi {
            val logger = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                Log.d("API", it)
            })
            logger.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TMDbApi::class.java)
        }
    }
}