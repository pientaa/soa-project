package com.pientaa.soapservice.model

import org.springframework.data.repository.Repository
import org.springframework.transaction.annotation.Transactional

interface PaymentRepository : Repository<PaymentEntity, String> {
    fun save(paymentEntity: PaymentEntity): PaymentEntity
    fun findAllByUserId(userId: String): List<PaymentEntity>
    fun findByTransactionId(transactionId: String): PaymentEntity?

    @Transactional
    fun deleteAllByUserId(userId: String)
}