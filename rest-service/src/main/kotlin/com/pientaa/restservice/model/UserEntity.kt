package com.pientaa.restservice.model

import com.pientaa.restservice.dto.UserDTO
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    val username: String,
    val firstName: String,
    val lastName: String
) {
    fun toUserDTO(): UserDTO =
        UserDTO(username, firstName, lastName)

    constructor(userDTO: UserDTO) : this(
        username = userDTO.username,
        firstName = userDTO.firstName,
        lastName = userDTO.lastName
    )
}