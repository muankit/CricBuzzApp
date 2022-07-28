package com.cricbuzz.domain.model

data class RestaurantDomain(
    val id: String,
    val name : String,
    val address : String,
    val cuisine_type : String,
    val reviews : List<ReviewDomain>
)
