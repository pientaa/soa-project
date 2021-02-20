package com.pientaa.gateway

import com.pientaa.gateway.dto.IssuePaymentDTO
import com.pientaa.gateway.dto.PaymentDTO
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController("/payments")
class PaymentController(
    private val paymentServiceClient: PaymentServiceClient
) {

    @GetMapping
    fun getUserPayments(@RequestParam userId: String): List<PaymentDTO> =
        paymentServiceClient.getUserPayments(userId)

    @PutMapping("/settle-payment")
    fun settlePayment(@RequestParam transactionId: String) {
        paymentServiceClient.settlePayment(transactionId)
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/issue-payment")
    fun issuePayment(@RequestBody issuePaymentDTO: IssuePaymentDTO) {
        paymentServiceClient.issuePayment(issuePaymentDTO)
    }
}