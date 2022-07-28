package com.cricbuzz.domain.resource

data class Resource<out T> constructor(
    var resourceState: ResourceState,
    val data: T?,
    val message: String?,
    val exception: Throwable? = null
) {
    companion object {
        fun <T> initial(): Resource<T> {
            return Resource(ResourceState.INITIAL, null, null)
        }

        fun <T> loading(): Resource<T> {
            return Resource(ResourceState.LOADING, null, null)
        }

        /*fun <T> success(data: T?): Resource<T> {
            return Resource(ResourceState.SUCCESS, data, null)
        }

        fun <T> error(message: String?): Resource<T> {
            return Resource(ResourceState.ERROR, null, message)
        }

        fun isLoading(resourceState: ResourceState) : Boolean {
            return resourceState == ResourceState.LOADING
        }*/
    }
}

enum class ResourceState {
    INITIAL,
    LOADING,
    SUCCESS,
    ERROR,
}

