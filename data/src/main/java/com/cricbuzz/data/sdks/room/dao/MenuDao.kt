package com.cricbuzz.data.sdks.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cricbuzz.data.sdks.room.entity.RmMenuEntity
import com.cricbuzz.domain.utils.Constants.MENU_TABLE_NAME

@Dao
interface MenuDao {
    @Insert
    suspend fun insert(list: List<RmMenuEntity>)

    @Query("SELECT COUNT(*) FROM $MENU_TABLE_NAME")
    suspend fun count(): Int
}