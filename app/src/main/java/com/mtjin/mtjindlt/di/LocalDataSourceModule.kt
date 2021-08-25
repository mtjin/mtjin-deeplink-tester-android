package com.mtjin.mtjindlt.di

import android.content.Context
import androidx.room.Room
import com.mtjin.mtjindlt.data.database.MtjinRoomDatabase
import com.mtjin.mtjindlt.data.main.source.local.MainDao
import com.mtjin.mtjindlt.data.main.source.local.MainLocalDataSource
import com.mtjin.mtjindlt.data.main.source.local.MainLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class LocalDataSourceModule {
    // Room database
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): MtjinRoomDatabase {
        return Room.databaseBuilder(
            context,
            MtjinRoomDatabase::class.java, "MtjinDLT.db"
        ).build()
    }

    // Local
    @Singleton
    @Provides
    fun provideMainLocalDataSource(mainDao: MainDao): MainLocalDataSource {
        return MainLocalDataSourceImpl(mainDao)
    }

    // Dao
    @Provides
    @Singleton
    fun provideUserDao(roomDatabase: MtjinRoomDatabase): MainDao =
        roomDatabase.mainDao()
}