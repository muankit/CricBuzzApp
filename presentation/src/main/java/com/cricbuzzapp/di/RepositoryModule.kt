package com.cricbuzzapp.di

import com.cricbuzz.data.repository.RestaurantsRepositoryImpl
import com.cricbuzz.domain.repository.RestaurantsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideRestaurantRepository(restaurantsRepositoryImpl: RestaurantsRepositoryImpl) : RestaurantsRepository {
        return restaurantsRepositoryImpl
    }
}