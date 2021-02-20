package com.pientaa.soapservice

import com.pientaa.IssuePayment
import com.pientaa.Payment
import com.pientaa.soapservice.model.PaymentEntity
import com.pientaa.soapservice.model.PaymentRepository
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun getUsersPayments(userId: String): List<Payment> =
        paymentRepository.findAllByUserId(userId)
            .map { it.toPayment() }

    fun issuePayment(issuePayment: IssuePayment) =
        paymentRepository.save(PaymentEntity(issuePayment)).toPayment()

    fun settlePayment(transactionId: String): Payment? =
        paymentRepository.findByTransactionId(transactionId)
            ?.settlePayment()
            ?.let(paymentRepository::save)
            ?.toPayment()
}