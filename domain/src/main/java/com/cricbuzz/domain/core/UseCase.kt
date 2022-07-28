package com.cricbuzz.domain.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCase<T> {

    abstract suspend fun run(): T

    fun invoke(
        scope: CoroutineScope,
        callback: UseCaseResponse<T>?,
    ) {
        scope.launch {
            try {
                val result = run()
                callback?.onSuccess(result)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

}