package com.pientaa.restservice

import com.pientaa.restservice.dto.UserDTO
import com.pientaa.restservice.infrastructure.EventPublisher
import com.pientaa.restservice.infrastructure.UserDeleted
import com.pientaa.restservice.model.UserEntity
import com.pientaa.restservice.model.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val eventPublisher: EventPublisher
) {
    fun getAll(): List<UserDTO> = userRepository.findAll()
        .map { it.toUserDTO() }

    fun createUser(user: UserDTO) {
        userRepository.save(UserEntity(user))
    }

    fun deleteUser(userId: String) {
        userRepository.findByIdOrNull(userId)
            ?.let { user ->
                userRepository.deleteById(user.id)
                    .also {
                        eventPublisher.publish(UserDeleted(user.id, user.firstName, user.lastName))
                    }
            }
    }
}