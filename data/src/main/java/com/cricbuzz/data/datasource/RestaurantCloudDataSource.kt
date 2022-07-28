package com.cricbuzz.data.datasource

import com.cricbuzz.data.entity.RestaurantEntity

interface RestaurantCloudDataSource {
    suspend fun fetchRestaurants(search: String) : RestaurantEntity
}