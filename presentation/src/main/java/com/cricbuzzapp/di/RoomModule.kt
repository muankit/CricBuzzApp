package com.cricbuzzapp.di

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.cricbuzz.data.sdks.room.db.RestaurantDatabase
import com.cricbuzz.domain.utils.Constants.RESTAURANT_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RestaurantDatabase::class.java, RESTAURANT_DATABASE)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideRestaurantDao(database: RestaurantDatabase) = database.getRestaurantDao()

    @Provides
    fun provideMenuDao(database: RestaurantDatabase) = database.getMenuDao()

    @Provides
    @Singleton
    fun resourcesProvider(@ApplicationContext context: Context): Resources = context.resources
}