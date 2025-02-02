package com.pientaa.restservice

import com.pientaa.restservice.dto.UserDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("userController")
class UserController(
    private val userService: UserService
) {
    @GetMapping
    fun getAllUsers() = userService.getAll()

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createUser(@RequestBody userDTO: UserDTO) = userService.createUser(userDTO)

    @DeleteMapping
    fun deleteUser(@RequestParam userId: String) = userService.deleteUser(userId)
}