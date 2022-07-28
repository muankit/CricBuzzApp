package com.cricbuzz.data.repository

import com.cricbuzz.data.datasource.RestaurantDataSourceFactory
import com.cricbuzz.data.mapper.mapEntityListToDomainList
import com.cricbuzz.data.mapper.mapEntityToDomain
import com.cricbuzz.domain.model.GenericDomain
import com.cricbuzz.domain.model.RestaurantDomain
import com.cricbuzz.domain.repository.RestaurantsRepository
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val restaurantDataSourceFactory: RestaurantDataSourceFactory
) : RestaurantsRepository {
    override suspend fun fetchRestaurants(search: String): List<RestaurantDomain> {
        return mapEntityListToDomainList(restaurantDataSourceFactory.getCacheDataSource().fetchRestaurants(search = search))
    }

    override suspend fun insertData(): GenericDomain {
        return restaurantDataSourceFactory.getCacheDataSource().insertData().mapEntityToDomain()
    }
}