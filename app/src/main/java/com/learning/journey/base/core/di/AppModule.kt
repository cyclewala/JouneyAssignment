package com.learning.journey.base.core.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.learning.journey.BuildConfig
import com.learning.journey.base.core.constant.AppConstants
import com.learning.journey.base.core.data.persistent.AppDatabase
import com.learning.journey.base.core.network.NetworkInterceptor
import com.learning.journey.base.core.utils.BaseHeaderUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(AppConstants.baseUrl)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideOkHttpClient(networkInterceptor: NetworkInterceptor): OkHttpClient =
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    networkInterceptor.intercept(chain)
                }
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .build()
        } else OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                networkInterceptor.intercept(chain)
            }
            .build()

    @Singleton
    @Provides
    fun provideBaseHeaderUtil(@ApplicationContext appContext: Context): BaseHeaderUtil =
        BaseHeaderUtil(appContext)

    @Singleton
    @Provides
    fun provideNetworkInterceptor(baseHeaderUtil: BaseHeaderUtil): NetworkInterceptor =
        NetworkInterceptor(baseHeaderUtil)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getDatabase(appContext)

}