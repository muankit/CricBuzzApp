package com.cricbuzz.data.sdks.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cricbuzz.domain.utils.Constants.RESTAURANT_TABLE_NAME

@Entity(tableName = RESTAURANT_TABLE_NAME)
data class RmRestaurantEntity(
    @PrimaryKey
    val id: Int,
    val name : String,
    val cuisine_type : String,
    val reviews : String
)
