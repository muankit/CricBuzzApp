package com.cricbuzz.domain.core

interface UseCaseResponse<Type> {
    suspend fun onSuccess(result: Type)

  //  suspend fun onError(apiError: ApiError?) // Not required here as no API call
}