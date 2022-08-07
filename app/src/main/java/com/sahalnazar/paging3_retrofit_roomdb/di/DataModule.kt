package com.sahalnazar.paging3_retrofit_roomdb.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sahalnazar.paging3_retrofit_roomdb.BuildConfig
import com.sahalnazar.paging3_retrofit_roomdb.data.db.AppDatabase
import com.sahalnazar.paging3_retrofit_roomdb.data.db.dao.MoviesDao
import com.sahalnazar.paging3_retrofit_roomdb.data.db.dao.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_room_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesKeysDao(appDataBase: AppDatabase): RemoteKeysDao = appDataBase.remoteKeysDao()

    @Provides
    @Singleton
    fun providesMoviesDao(appDataBase: AppDatabase): MoviesDao = appDataBase.remoteMoviesDao()
}