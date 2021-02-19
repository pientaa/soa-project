package com.pientaa.soapservice

import generated.Payment
import org.springframework.data.repository.Repository

interface PaymentRepository : Repository<Payment, String>