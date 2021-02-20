package com.pientaa.gateway

import com.pientaa.gateway.dto.IssuePaymentDTO
import com.pientaa.gateway.wsdl.IssuePayment
import com.pientaa.gateway.wsdl.IssuePaymentRequest
import com.pientaa.gateway.wsdl.IssuePaymentResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.ws.client.core.support.WebServiceGatewaySupport
import org.springframework.ws.soap.client.core.SoapActionCallback

class PaymentServiceClient : WebServiceGatewaySupport() {
    companion object {
        private val log = LoggerFactory.getLogger(PaymentServiceClient::class.java)
    }

    @Value("\${services.payment.wsdl.url}")
    lateinit var paymentsWsdlUrl: String

    fun issuePayment(issuePaymentDTO: IssuePaymentDTO) {
        val request = IssuePaymentRequest()
            .apply {
                issuePayment = IssuePayment().apply {
                    userId = issuePaymentDTO.userId
                    amount = issuePaymentDTO.amount
                }
            }
        log.info("Issue payment: ${issuePaymentDTO.amount} for user: ${issuePaymentDTO.userId}")

        webServiceTemplate
            .marshalSendAndReceive(
                paymentsWsdlUrl, request,
                SoapActionCallback(
                    "http://pientaa.com/IssuePaymentRequest"
                )
            ) as IssuePaymentResponse
    }
}