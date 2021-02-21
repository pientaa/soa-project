package com.pientaa.soapservice.infrastructure

data class RollbackUserDeleted(
    val userId: String,
    val firstName: String,
    val lastName: String
) {
    constructor(event: UserDeleted) : this(
        userId = event.userId,
        firstName = event.firstName,
        lastName = event.lastName
    )
}