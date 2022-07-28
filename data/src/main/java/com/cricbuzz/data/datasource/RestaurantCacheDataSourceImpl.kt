package com.cricbuzz.data.datasource

import com.cricbuzz.data.cache.RestaurantCache
import com.cricbuzz.data.entity.GenericEntity
import com.cricbuzz.data.entity.RestaurantEntity
import javax.inject.Inject

class RestaurantCacheDataSourceImpl @Inject constructor(
    private val restaurantCache: RestaurantCache
) : RestaurantCacheDataSource {
    override suspend fun fetchRestaurants(search: String): List<RestaurantEntity> {
        return restaurantCache.fetchRestaurants(search)
    }

    override suspend fun insertData(): GenericEntity {
        return restaurantCache.insertData()
    }
}