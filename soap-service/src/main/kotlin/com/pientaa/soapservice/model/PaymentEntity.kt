package com.pientaa.soapservice.model

import com.pientaa.soapservice.model.PaymentEntity.PaymentEntityStatus
import generated.Payment
import generated.PaymentStatus
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
    var status: PaymentEntityStatus
) {
    constructor(payment: Payment) : this(
        userId = payment.userId,
        amount = payment.amount,
        status = payment.status.toPaymentEntityStatus()
    )

    enum class PaymentEntityStatus {
        ISSUED,
        SETTLED
    }
}

fun PaymentStatus.toPaymentEntityStatus(): PaymentEntityStatus = when (this) {
    PaymentStatus.ISSUED -> PaymentEntityStatus.ISSUED
    PaymentStatus.SETTLED -> PaymentEntityStatus.SETTLED
}