package com.jmc.tecnicaltestfifjmc.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmc.tecnicaltestfifjmc.data.source.local.database.dao.PersonDao

import com.jmc.tecnicaltestfifjmc.data.source.local.entity.PersonEntity

private const val DATABASE_VERSION = 1

@Database(entities = [PersonEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class FifDatabase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao

}