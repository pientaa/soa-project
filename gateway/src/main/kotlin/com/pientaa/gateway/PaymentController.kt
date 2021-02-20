package com.pientaa.gateway

import com.pientaa.gateway.dto.IssuePaymentDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/payments")
class PaymentController(
    private val paymentServiceClient: PaymentServiceClient
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/issue-payment")
    fun issuePayment(@RequestBody issuePaymentDTO: IssuePaymentDTO) {
        paymentServiceClient.issuePayment(issuePaymentDTO)
    }
}