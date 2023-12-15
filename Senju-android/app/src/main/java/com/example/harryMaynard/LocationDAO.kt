package com.example.harryMaynard

import androidx.room.*
@Dao
interface LocationDAO {

    @Query("SELECT * FROM locations")
    fun getAllLocations(): List<LocationPOI>

    @Insert
    fun insert(location: LocationPOI) : Long

    @Update
    fun update(location: LocationPOI) : Int

    @Delete
    fun delete(location: LocationPOI) : Int
}