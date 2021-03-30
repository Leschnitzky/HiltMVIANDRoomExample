package com.example.roomhiltexample.room

import com.example.roomhiltexample.util.EntityMapper
import com.example.roomhiltexample.model.User
import javax.inject.Inject

class RoomEntityMapper @Inject constructor(): EntityMapper<User, UserCacheEntity> {
    override fun fromBaseToEntity(base: User): UserCacheEntity {
        return UserCacheEntity(
            userLogin = base.username,
            password = base.password
        )
    }

    override fun fromEntityToBase(entity: UserCacheEntity): User {
        return User(
            username = entity.userLogin,
            password = entity.password
        )
    }

    fun mapFromEntityList(entities : List<UserCacheEntity>) : List<User>{
        return entities.map { fromEntityToBase(it) }
    }


}