package com.example.roomhiltexample.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserCacheEntity(
        @PrimaryKey
        @ColumnInfo(name = "user_login") var userLogin: String,
        @ColumnInfo(name = "password") val password: String
)
