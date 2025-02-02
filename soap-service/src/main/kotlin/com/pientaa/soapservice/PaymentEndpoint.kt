package com.pientaa.soapservice

import com.pientaa.GetUserPaymentsRequest
import com.pientaa.GetUserPaymentsResponse
import com.pientaa.IssuePaymentRequest
import com.pientaa.IssuePaymentResponse
import com.pientaa.SettlePaymentRequest
import com.pientaa.SettlePaymentResponse
import org.springframework.ws.server.endpoint.annotation.Endpoint
import org.springframework.ws.server.endpoint.annotation.PayloadRoot
import org.springframework.ws.server.endpoint.annotation.RequestPayload
import org.springframework.ws.server.endpoint.annotation.ResponsePayload

@Endpoint
class CountryEndpoint(private val paymentService: PaymentService) {

    companion object {
        const val NAMESPACE_URI = "http://pientaa.com"
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "issuePaymentRequest")
    @ResponsePayload
    fun issuePayment(@RequestPayload request: IssuePaymentRequest): IssuePaymentResponse {
        return IssuePaymentResponse()
            .apply {
                payment = paymentService.issuePayment(request.issuePayment)
            }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "settlePaymentRequest")
    @ResponsePayload
    fun settlePayment(@RequestPayload request: SettlePaymentRequest): SettlePaymentResponse {
        return SettlePaymentResponse()
            .apply {
                payment = paymentService.settlePayment(request.transactionId)
            }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserPaymentsRequest")
    @ResponsePayload
    fun getUserPayments(@RequestPayload request: GetUserPaymentsRequest): GetUserPaymentsResponse {
        return GetUserPaymentsResponse()
            .apply {
                payments.addAll(paymentService.getUsersPayments(request.userId))
            }
    }
}