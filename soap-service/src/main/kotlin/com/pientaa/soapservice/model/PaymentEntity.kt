package com.pientaa.soapservice.model

import com.pientaa.IssuePayment
import com.pientaa.Payment
import com.pientaa.PaymentStatus
import com.pientaa.soapservice.model.PaymentEntity.PaymentEntityStatus
import com.pientaa.soapservice.model.PaymentEntity.PaymentEntityStatus.*
import java.math.BigDecimal
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class PaymentEntity(
    @Id
    val transactionId: String = UUID.randomUUID().toString(),
    val userId: String = "nonInitialized",
    val amount: BigDecimal = BigDecimal(0),
    var status: PaymentEntityStatus = ERROR
) {
    fun settlePayment() = this.apply {
        status = SETTLED
    }

    fun toPayment(): Payment =
        Payment().apply {
            transactionId = this@PaymentEntity.transactionId
            userId = this@PaymentEntity.userId
            amount = this@PaymentEntity.amount
            status = this@PaymentEntity.status.toPaymentStatus()
        }

    constructor(payment: Payment) : this(
        userId = payment.userId,
        amount = payment.amount,
        status = payment.status.toPaymentEntityStatus()
    )

    constructor(issuePayment: IssuePayment) : this(
        userId = issuePayment.userId,
        amount = issuePayment.amount,
        status = ISSUED
    )

    enum class PaymentEntityStatus {
        ERROR,
        ISSUED,
        SETTLED
    }
}

fun PaymentEntityStatus.toPaymentStatus(): PaymentStatus = when (this) {
    ISSUED -> PaymentStatus.ISSUED
    SETTLED -> PaymentStatus.SETTLED
    else -> throw IllegalStateException("ERROR")
}

fun PaymentStatus.toPaymentEntityStatus(): PaymentEntityStatus = when (this) {
    PaymentStatus.ISSUED -> ISSUED
    PaymentStatus.SETTLED -> SETTLED
}