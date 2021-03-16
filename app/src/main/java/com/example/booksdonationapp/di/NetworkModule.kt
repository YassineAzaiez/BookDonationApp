package com.example.booksdonationapp.di

import android.content.Context
import com.example.booksdonationapp.BuildConfig
import com.example.booksdonationapp.BuildConfig.BASE_URL
import com.example.booksdonationapp.core.WebServiceInterface
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    private const val CACHE_SIZE = 10 * 1024 * 1024 // 10 MB
    private const val CONNECT_TIMEOUT = "10"
    private const val WRITE_TIMEOUT = "60"
    private const val READ_TIMEOUT = "30"
    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"


    @Provides
    @Singleton
    fun providesOkHttpClient(cache: Cache): OkHttpClient {
        val client = OkHttpClient.Builder()
            //.addInterceptor(MyInterceptor())
            //.addNetworkInterceptor(networkCashInterceptor())
            .cache(cache)
            .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addNetworkInterceptor(StethoInterceptor())
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return client.build()

    }


    @Provides
    @Singleton
    fun providesOkHttpCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, CACHE_SIZE.toLong())
    }

    @JvmStatic
    @Provides
    @Singleton
    fun providesRetrofitInstance(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @JvmStatic
    @Provides
    @Singleton
    fun provideWebService(retrofit: Retrofit): WebServiceInterface =
        retrofit.create(WebServiceInterface::class.java)





}
