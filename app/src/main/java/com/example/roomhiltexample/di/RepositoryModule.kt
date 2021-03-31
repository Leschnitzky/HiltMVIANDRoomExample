package com.example.roomhiltexample.di

import androidx.room.Dao
import com.example.roomhiltexample.repository.UserRepository
import com.example.roomhiltexample.room.RoomEntityMapper
import com.example.roomhiltexample.room.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        userDao: UsersDao,
        mapper: RoomEntityMapper
    ) : UserRepository {
        return UserRepository(userDao,mapper)
    }
}