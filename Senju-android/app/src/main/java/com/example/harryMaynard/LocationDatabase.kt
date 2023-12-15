package com.example.harryMaynard

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LocationPOI::class), version = 1, exportSchema = false)
public abstract class LocationDatabase: RoomDatabase() {
    abstract fun LocationDAO(): LocationDAO

    companion object {
        private var instance: LocationDatabase? = null

        fun getDatabase(ctx:Context) : LocationDatabase{
            var tmpInstance = instance
            if(tmpInstance == null) {
                tmpInstance = Room.databaseBuilder(
                    ctx.applicationContext,
                    LocationDatabase::class.java,
                    "LocationDatabase"
                ).build()
                instance = tmpInstance
            }
            return tmpInstance
        }
    }
}