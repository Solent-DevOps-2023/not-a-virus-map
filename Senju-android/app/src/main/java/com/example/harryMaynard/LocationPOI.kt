package com.example.harryMaynard

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="locations")

data class LocationPOI(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name="name") val name: String,
    @ColumnInfo(name="description") var description: String,
    @ColumnInfo(name="type") var type: String,
    @ColumnInfo(name="lon") var lon: Double,
    @ColumnInfo(name="lat") var lat: Double
)