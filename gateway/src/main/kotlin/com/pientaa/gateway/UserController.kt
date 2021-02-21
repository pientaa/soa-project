package com.pientaa.gateway

import com.pientaa.gateway.dto.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/users")
@RestController
class UserController(
    private val userServiceClient: UserServiceClient
) {

    @GetMapping
    fun getAllUsers() = userServiceClient.getAllUsers()

    @DeleteMapping
    fun deleteUser(@RequestParam userId: String) = userServiceClient.deleteUser(userId)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(@RequestBody createUserDTO: UserDTO) = userServiceClient.createUser(createUserDTO)
}