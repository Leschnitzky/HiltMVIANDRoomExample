package com.example.roomhiltexample.repository

import com.example.roomhiltexample.model.User
import com.example.roomhiltexample.room.RoomEntityMapper
import com.example.roomhiltexample.room.UsersDao
import com.example.roomhiltexample.util.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import java.util.concurrent.Flow

class UserRepository
constructor(
    private val usersDao: UsersDao,
    private val mapper: RoomEntityMapper
){
    suspend fun getUsers() = flow {
        emit(DataState.Loading)
        kotlinx.coroutines.delay(1000)
        try {
            val users = usersDao.get()
            emit(DataState.Success(mapper.mapFromEntityList(users)))
        } catch (e: Exception){
            emit(DataState.Error(e))
        }

    }
}