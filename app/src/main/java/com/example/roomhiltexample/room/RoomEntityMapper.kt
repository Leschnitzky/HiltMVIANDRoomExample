package com.example.roomhiltexample.room

import com.example.roomhiltexample.util.EntityMapper
import com.example.roomhiltexample.model.User
import javax.inject.Inject

class RoomEntityMapper @Inject constructor(): EntityMapper<User, UserCacheEntity> {
    override fun fromBaseToEntity(base: User): UserCacheEntity {
        return UserCacheEntity(
            userLogin = base.username,
            password = base.password,
            uid = base.id
        )
    }

    override fun fromEntityToBase(entity: UserCacheEntity): User {
        return User(
            username = entity.userLogin,
            password = entity.password,
            id = entity.uid
        )
    }

    fun mapFromEntityList(entities : List<UserCacheEntity>) : List<User>{
        return entities.map { fromEntityToBase(it) }
    }


}