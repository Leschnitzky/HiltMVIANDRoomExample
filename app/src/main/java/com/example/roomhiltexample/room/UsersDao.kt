package com.example.roomhiltexample.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert()
    suspend fun insert(userRoomEntity: UserCacheEntity) : Long

    @Query("SELECT * FROM users")
    suspend fun get(): List<UserCacheEntity>

}