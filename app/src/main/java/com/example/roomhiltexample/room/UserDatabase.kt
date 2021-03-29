package com.example.roomhiltexample.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserCacheEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UsersDao

    companion object{
        val DATABASE_NAME: String = "user_db"
    }
}