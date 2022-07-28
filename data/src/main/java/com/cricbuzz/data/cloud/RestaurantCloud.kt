package com.cricbuzz.data.cloud

import com.cricbuzz.data.entity.RestaurantEntity

interface RestaurantCloud {
    suspend fun fetchRestaurants(search : String) : RestaurantEntity
}