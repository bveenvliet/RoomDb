package com.example.roomdb.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LocationDao {
    @Query("SELECT * FROM location")
    fun getAll(): List<Location>

    @Query("SELECT * FROM location WHERE id IN (:ids)")
    fun getAll(ids: IntArray): List<Location>

    @Query("SELECT * FROM location WHERE label LIKE :label")
    fun getAll(label: String): List<Location>

    @Insert
    fun insertAll(vararg locations: Location)

    @Delete
    fun delete(location: Location)

}