package com.example.roomhiltexample.di

import android.content.Context
import androidx.room.Room
import com.example.roomhiltexample.util.EntityMapper
import com.example.roomhiltexample.model.User
import com.example.roomhiltexample.room.RoomEntityMapper
import com.example.roomhiltexample.room.UserCacheEntity
import com.example.roomhiltexample.room.UserDatabase
import com.example.roomhiltexample.room.UsersDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideMapper() : EntityMapper<User, UserCacheEntity> {
        return RoomEntityMapper();
    }


    @Singleton
    @Provides
    fun provideDB(@ApplicationContext context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDAO(userDatabase: UserDatabase): UsersDao {
        return userDatabase.userDao()
    }
}