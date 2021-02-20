package com.pientaa.soapservice

import com.pientaa.soapservice.model.PaymentEntity
import org.springframework.data.repository.Repository

interface PaymentRepository : Repository<PaymentEntity, String> {
    fun save(paymentEntity: PaymentEntity): PaymentEntity
    fun findAllByUserId(userId: String): List<PaymentEntity>
}