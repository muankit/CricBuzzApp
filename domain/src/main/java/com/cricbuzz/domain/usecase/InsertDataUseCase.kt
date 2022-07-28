package com.cricbuzz.domain.usecase

import com.cricbuzz.domain.core.UseCase
import com.cricbuzz.domain.model.GenericDomain
import com.cricbuzz.domain.repository.RestaurantsRepository
import javax.inject.Inject

class InsertDataUseCase @Inject constructor(
    private val restaurantsRepository: RestaurantsRepository
) : UseCase<GenericDomain>() {

    override suspend fun run(): GenericDomain {
        return restaurantsRepository.insertData()
    }
}