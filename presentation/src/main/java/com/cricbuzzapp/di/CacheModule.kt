package com.cricbuzzapp.di

import com.cricbuzz.data.cache.RestaurantCache
import com.cricbuzz.data.cache.RestaurantCacheImpl
import com.cricbuzz.data.cloud.RestaurantCloud
import com.cricbuzz.data.cloud.RestaurantCloudImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideRestaurantCache(restaurantCacheImpl: RestaurantCacheImpl) : RestaurantCache {
        return restaurantCacheImpl
    }
}