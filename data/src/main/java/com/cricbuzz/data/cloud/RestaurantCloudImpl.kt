package com.cricbuzz.data.cloud

import com.cricbuzz.data.entity.RestaurantEntity
import javax.inject.Inject

class RestaurantCloudImpl @Inject constructor() : RestaurantCloud {
    override suspend fun fetchRestaurants(search: String): RestaurantEntity {
        TODO("Not yet implemented")
    }
}