package com.cricbuzz.data.sdks.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cricbuzz.domain.utils.Constants.MENU_TABLE_NAME

@Entity(tableName = MENU_TABLE_NAME)
data class RmMenuEntity(
    @PrimaryKey
    val id: Int ,
    val restaurantId : Int,
    val name : String,
    val description : String,

)
