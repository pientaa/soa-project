package com.pientaa.gateway.dto

import java.math.BigDecimal

data class IssuePaymentDTO(
    val userId: String,
    val amount: BigDecimal
)