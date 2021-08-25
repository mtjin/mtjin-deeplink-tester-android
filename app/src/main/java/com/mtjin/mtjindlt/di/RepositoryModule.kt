package com.mtjin.mtjindlt.di

import com.mtjin.mtjindlt.data.main.source.MainRepository
import com.mtjin.mtjindlt.data.main.source.MainRepositoryImpl
import com.mtjin.mtjindlt.data.main.source.local.MainLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(mainLocalRepository: MainLocalDataSource): MainRepository {
        return MainRepositoryImpl(mainLocalRepository)
    }

}