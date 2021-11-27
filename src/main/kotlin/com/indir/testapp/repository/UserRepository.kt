package com.indir.testapp.repository

import com.indir.testapp.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository :JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
}