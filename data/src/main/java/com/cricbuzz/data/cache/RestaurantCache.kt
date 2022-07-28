package com.cricbuzz.data.cache

import com.cricbuzz.data.entity.GenericEntity
import com.cricbuzz.data.entity.RestaurantEntity

interface RestaurantCache {
    suspend fun fetchRestaurants(search : String) : List<RestaurantEntity>
    suspend fun insertData() : GenericEntity
}