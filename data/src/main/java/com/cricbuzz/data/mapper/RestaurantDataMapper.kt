package com.cricbuzz.data.mapper

import com.cricbuzz.data.entity.GenericEntity
import com.cricbuzz.data.entity.RestaurantEntity
import com.cricbuzz.data.sdks.room.entity.RmRestaurantEntity
import com.cricbuzz.domain.model.GenericDomain
import com.cricbuzz.domain.model.RestaurantDomain
import com.cricbuzz.domain.model.ReviewDomain
import org.json.JSONArray

fun GenericEntity.mapEntityToDomain() : GenericDomain {
    return GenericDomain(
        isSuccess = isSuccess
    )
}

fun mapEntityToDomain(restaurantEntity: RestaurantEntity): RestaurantDomain {
    return RestaurantDomain(
        id = restaurantEntity.id,
        name = restaurantEntity.name,
        address = "",
        cuisine_type = restaurantEntity.cuisine_type,
        reviews = mapReviews(restaurantEntity.reviews)
    )
}

fun mapReviews(string : String) : List<ReviewDomain> {
    val reviews = JSONArray(string)

    val list = arrayListOf<ReviewDomain>()

    for(i in 0 until reviews.length()){
        val item = reviews.getJSONObject(i)

        val name = item.getString("name")
        val rating = item.getString("rating")
        val comment = item.getString("comments")

        list.add(
            ReviewDomain(
               name, rating, comment
            )
        )
    }

    return list
}

fun mapEntityListToDomainList(restaurants: List<RestaurantEntity>) : List<RestaurantDomain> {
    return restaurants.map { restaurant ->
        mapEntityToDomain(restaurant)
    }
}

fun mapRmEntityToEntity(restaurantEntity: RmRestaurantEntity): RestaurantEntity {
    return RestaurantEntity(
        id = restaurantEntity.id.toString(),
        name = restaurantEntity.name,
        address = "",
        cuisine_type = restaurantEntity.cuisine_type,
        reviews = restaurantEntity.reviews
    )
}

fun mapRmListToEntityList(restaurants: List<RmRestaurantEntity>) : List<RestaurantEntity> {
    return restaurants.map { restaurant ->
        mapRmEntityToEntity(restaurant)
    }
}

