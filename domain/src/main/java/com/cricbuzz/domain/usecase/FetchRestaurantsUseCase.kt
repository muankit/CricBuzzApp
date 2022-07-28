package com.cricbuzz.domain.usecase

import com.cricbuzz.domain.core.UseCaseParams
import com.cricbuzz.domain.model.RestaurantDomain
import com.cricbuzz.domain.repository.RestaurantsRepository
import javax.inject.Inject

class FetchRestaurantsUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) : UseCaseParams<List<RestaurantDomain>, FetchRestaurantsUseCase.Params>() {

    data class Params(
        val search: String
    )

    override suspend fun run(params: Params): List<RestaurantDomain> {
        return restaurantsRepository.fetchRestaurants(params.search)
    }
}