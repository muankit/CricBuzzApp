package com.cricbuzz.data.sdks.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cricbuzz.data.sdks.room.dao.MenuDao
import com.cricbuzz.data.sdks.room.dao.RestaurantsDao
import com.cricbuzz.data.sdks.room.entity.RmMenuEntity
import com.cricbuzz.data.sdks.room.entity.RmRestaurantEntity


@Database(
    version = 1,
    exportSchema = false,
    entities = [
        RmMenuEntity::class,
        RmRestaurantEntity::class
    ]
)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun getRestaurantDao(): RestaurantsDao
    abstract fun getMenuDao(): MenuDao
}