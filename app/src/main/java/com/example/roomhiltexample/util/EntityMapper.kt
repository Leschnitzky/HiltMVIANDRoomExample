package com.example.roomhiltexample.util

interface EntityMapper<Base, Entity> {
    fun fromBaseToEntity(base: Base) : Entity
    fun fromEntityToBase(entity: Entity) : Base
}