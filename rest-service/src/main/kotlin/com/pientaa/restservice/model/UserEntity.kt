package com.pientaa.restservice.model

import com.pientaa.restservice.dto.UserDTO
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String
) {
    fun toUserDTO(): UserDTO =
        UserDTO(id, firstName, lastName)

    constructor(userDTO: UserDTO) : this(
        firstName = userDTO.firstName,
        lastName = userDTO.lastName
    )
}