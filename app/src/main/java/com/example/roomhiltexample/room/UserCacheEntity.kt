package com.example.roomhiltexample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserCacheEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "user_login") val userLogin: String,
    @ColumnInfo(name = "password") val password: String
)
