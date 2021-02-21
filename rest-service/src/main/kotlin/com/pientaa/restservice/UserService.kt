package com.pientaa.restservice

import com.pientaa.restservice.dto.UserDTO
import com.pientaa.restservice.model.UserEntity
import com.pientaa.restservice.model.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getAll(): List<UserDTO> = userRepository.findAll()
        .map { it.toUserDTO() }

    fun createUser(user: UserDTO) {
        userRepository.save(UserEntity(user))
    }

    fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }
}