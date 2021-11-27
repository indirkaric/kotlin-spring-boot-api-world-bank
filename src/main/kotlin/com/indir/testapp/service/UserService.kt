package com.indir.testapp.service

import com.indir.testapp.dto.CreateUserDto
import com.indir.testapp.dto.UserDto
import com.indir.testapp.entity.User
import com.indir.testapp.exception.error.BadRequestException
import com.indir.testapp.exception.error.NotFoundException
import com.indir.testapp.exception.error.RestApiError
import com.indir.testapp.mapper.UserMapper
import com.indir.testapp.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var userRepository: UserRepository

    fun getUsers(): List<UserDto> = userRepository.findAll().map { user -> UserMapper.toDto(user) }

    fun getUser(id: Long): UserDto = UserMapper.toDto(findById(id))

    private fun findById(id: Long): User {
        val user = userRepository.findById(id)
        if (user.isPresent)
            return user.get()
        throw NotFoundException(RestApiError.USER_NOT_FOUND)
    }

    fun saveUser(createUserDto: CreateUserDto) {
        val user = userRepository.findByUsername(createUserDto.username)
        if (user != null)
            throw BadRequestException(RestApiError.USER_ALREADY_EXISTS)
        userRepository.save(UserMapper.toEntity(createUserDto))
    }
}