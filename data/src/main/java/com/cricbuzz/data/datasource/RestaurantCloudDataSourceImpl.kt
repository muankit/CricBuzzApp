package com.cricbuzz.data.datasource

import com.cricbuzz.data.cloud.RestaurantCloud
import com.cricbuzz.data.entity.RestaurantEntity
import javax.inject.Inject

class RestaurantCloudDataSourceImpl @Inject constructor(
    private val restaurantCloud: RestaurantCloud
) : RestaurantCloudDataSource{
    override suspend fun fetchRestaurants(search: String): RestaurantEntity {
        return restaurantCloud.fetchRestaurants(search)
    }
}