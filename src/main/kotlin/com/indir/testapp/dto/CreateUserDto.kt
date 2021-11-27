package com.indir.testapp.dto

import javax.validation.constraints.NotBlank

data class CreateUserDto (
    @NotBlank(message = "First name can not be blank")
    var firstName: String,

    @NotBlank(message = "Last name can not be blank")
    var lastName: String,

    @NotBlank(message = "username can not be blank")
    var username: String
)