package com.cricbuzz.data.sdks.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cricbuzz.data.sdks.room.entity.RmRestaurantEntity
import com.cricbuzz.domain.utils.Constants.RESTAURANT_TABLE_NAME

@Dao
interface RestaurantsDao {
    @Insert
    suspend fun insert(restaurantEntity: List<RmRestaurantEntity>)

    @Query("SELECT COUNT(*) FROM $RESTAURANT_TABLE_NAME")
    suspend fun count(): Int

    @Query("SELECT * FROM $RESTAURANT_TABLE_NAME WHERE cuisine_type like '%' || :search || '%'")
    suspend fun fetchRestaurant(search: String) : List<RmRestaurantEntity>
}