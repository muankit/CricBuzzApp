package com.cricbuzz.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCaseParams<T, in Parameter> {

    abstract suspend fun run(params: Parameter): T

    fun invoke(
        scope: CoroutineScope,
        callback: UseCaseResponse<T>?,
        params: Parameter,
    ) {
        scope.launch {
            try {
                val result = run(params)
                callback?.onSuccess(result)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

}