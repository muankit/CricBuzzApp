package com.cricbuzz.domain.repository

import com.cricbuzz.domain.model.GenericDomain
import com.cricbuzz.domain.model.RestaurantDomain

interface RestaurantsRepository {
    suspend fun fetchRestaurants(search : String) : List<RestaurantDomain>
    suspend fun insertData() : GenericDomain
}