package com.pientaa.soapservice

import com.pientaa.soapservice.model.PaymentEntity
import com.pientaa.soapservice.model.PaymentEntity.PaymentEntityStatus.SETTLED
import generated.Payment
import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository
) {

    fun issuePayment(payment: Payment) {
        paymentRepository.save(PaymentEntity(payment))
    }

    fun settlePayment(transactionId: String) {
        paymentRepository.findByTransactionId(transactionId)
            ?.apply {
                status = SETTLED
            }
            ?.let(paymentRepository::save)
    }
}