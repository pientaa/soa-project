package com.pientaa.soapservice

import com.pientaa.soapservice.model.PaymentEntity
import generated.Payment
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun getUsersPayments(userId: String): List<Payment> =
        paymentRepository.findAllByUserId(userId)
            .map { it.toPayment() }

    fun issuePayment(payment: Payment) {
        paymentRepository.save(PaymentEntity(payment))
    }

    fun settlePayment(transactionId: String) {
        paymentRepository.findByTransactionId(transactionId)
            ?.settlePayment()
            ?.let(paymentRepository::save)
    }
}