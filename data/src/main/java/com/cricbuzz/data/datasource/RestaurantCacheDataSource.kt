package com.cricbuzz.data.datasource

import com.cricbuzz.data.entity.GenericEntity
import com.cricbuzz.data.entity.RestaurantEntity

interface RestaurantCacheDataSource {
    suspend fun fetchRestaurants(search : String) : List<RestaurantEntity>
    suspend fun insertData() : GenericEntity
}