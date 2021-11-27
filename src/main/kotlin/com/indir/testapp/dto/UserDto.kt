package com.indir.testapp.dto

import java.util.*

data class UserDto (
    var id:Long,
    var firstName: String,
    var lastName: String,
    var username: String,
    var createdAt: Date
)