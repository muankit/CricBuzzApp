package com.cricbuzz.data.datasource

import javax.inject.Inject

class RestaurantDataSourceFactory @Inject constructor(
    private val restaurantCloudDataSource: RestaurantCloudDataSource,
    private val restaurantCacheDataSource: RestaurantCacheDataSource
) {
    fun getCloudDataSource() = restaurantCloudDataSource
    fun getCacheDataSource() = restaurantCacheDataSource
}