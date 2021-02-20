package com.pientaa.soapservice

import com.pientaa.IssuePaymentRequest
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

    fun issuePayment(issuePaymentRequest: IssuePaymentRequest) =
        paymentRepository.save(PaymentEntity(issuePaymentRequest.issuePayment)).toPayment()

    fun settlePayment(transactionId: String) {
        paymentRepository.findByTransactionId(transactionId)
            ?.settlePayment()
            ?.let(paymentRepository::save)
    }
}