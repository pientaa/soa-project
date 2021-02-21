package com.pientaa.soapservice.infrastructure

data class RollbackUserDeleted(
    val username: String,
    val firstName: String,
    val lastName: String
) {
    constructor(event: UserDeleted) : this(
        username = event.username,
        firstName = event.firstName,
        lastName = event.lastName
    )
}