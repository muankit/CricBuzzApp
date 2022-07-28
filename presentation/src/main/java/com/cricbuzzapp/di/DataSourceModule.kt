package com.cricbuzzapp.di

import com.cricbuzz.data.datasource.RestaurantCacheDataSource
import com.cricbuzz.data.datasource.RestaurantCacheDataSourceImpl
import com.cricbuzz.data.datasource.RestaurantCloudDataSource
import com.cricbuzz.data.datasource.RestaurantCloudDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideRestaurantDataSource(restaurantCloudDataSourceImpl: RestaurantCloudDataSourceImpl) : RestaurantCloudDataSource  {
        return restaurantCloudDataSourceImpl
    }

    @Singleton
    @Provides
    fun provideRestaurantCacheSource(restaurantCacheDataSourceImpl: RestaurantCacheDataSourceImpl) : RestaurantCacheDataSource  {
        return restaurantCacheDataSourceImpl
    }
}