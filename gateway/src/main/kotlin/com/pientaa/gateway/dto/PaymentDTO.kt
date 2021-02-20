package com.pientaa.gateway.dto

import com.pientaa.gateway.wsdl.Payment
import com.pientaa.gateway.wsdl.PaymentStatus
import java.math.BigDecimal

data class PaymentDTO(
    val transactionId: String,
    val userId: String,
    val amount: BigDecimal,
    val status: PaymentStatusDTO
) {
    constructor(payment: Payment) : this(
        transactionId = payment.transactionId,
        userId = payment.userId,
        amount = payment.amount,
        status = payment.status.toPaymentStatusDTO()
    )

    enum class PaymentStatusDTO {
        ISSUED,
        SETTLED
    }
}

fun PaymentStatus.toPaymentStatusDTO(): PaymentDTO.PaymentStatusDTO = when (this) {
    PaymentStatus.ISSUED -> PaymentDTO.PaymentStatusDTO.ISSUED
    PaymentStatus.SETTLED -> PaymentDTO.PaymentStatusDTO.SETTLED
}