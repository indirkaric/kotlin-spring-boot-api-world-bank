package com.indir.testapp.controller

import com.indir.testapp.dto.CreateUserDto
import com.indir.testapp.dto.UserDto
import com.indir.testapp.entity.User
import com.indir.testapp.mapper.UserMapper
import com.indir.testapp.repository.UserRepository
import com.indir.testapp.service.UserService
import io.swagger.annotations.ApiOperation
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid


@RestController
@RequestMapping("/api/v1/users/")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping
    @ApiOperation(value = "Get users")
    fun getUsers(): ResponseEntity<List<UserDto>> = ResponseEntity(userService.getUsers(), HttpStatus.OK)

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserDto> = ResponseEntity(userService.getUser(id), HttpStatus.OK)

    @PostMapping
    @ApiOperation(value = "Save user")
    fun saveUser(@RequestBody @Valid createUserDto: CreateUserDto): ResponseEntity<Void> {
        userService.saveUser(createUserDto)
        return ResponseEntity(HttpStatus.CREATED)
    }

}