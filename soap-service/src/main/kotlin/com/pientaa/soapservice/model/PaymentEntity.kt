package com.pientaa.soapservice.model

import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PaymentEntity(
    @Id
    val transactionId: String = UUID.randomUUID().toString(),
    val userId: String,
    val amount: BigDecimal,
    val status: PaymentStatus
){

    enum class PaymentStatus {
        ISSUED,
        SETTLED
    }
}