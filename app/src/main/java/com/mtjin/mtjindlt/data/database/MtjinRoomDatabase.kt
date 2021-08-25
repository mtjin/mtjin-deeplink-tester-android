package com.mtjin.mtjindlt.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mtjin.mtjindlt.data.main.DeepLinkUrl
import com.mtjin.mtjindlt.data.main.source.local.MainDao

@Database(
    entities = [DeepLinkUrl::class],
    version = 1,
    exportSchema = false
)
abstract class MtjinRoomDatabase : RoomDatabase() {
    abstract fun mainDao(): MainDao
}