package com.pientaa.restservice.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val firstName: String,
    val lastName: String
)