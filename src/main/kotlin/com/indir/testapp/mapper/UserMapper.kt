package com.indir.testapp.mapper

import com.indir.testapp.dto.CreateUserDto
import com.indir.testapp.dto.UserDto
import com.indir.testapp.entity.User

class UserMapper {
    companion object {
        fun toDto(user: User): UserDto =
            UserDto(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username,
                createdAt = user.createdAt
            )

        fun toEntity(user: CreateUserDto): User =
            User(
                firstName = user.firstName,
                lastName = user.lastName,
                username = user.username
            )
    }
}