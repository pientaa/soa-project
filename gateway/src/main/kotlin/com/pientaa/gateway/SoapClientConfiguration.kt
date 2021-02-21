package com.pientaa.gateway

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.oxm.jaxb.Jaxb2Marshaller

@Configuration
class SoapClientConfiguration {
    @Bean
    fun marshaller(): Jaxb2Marshaller {
        val marshaller = Jaxb2Marshaller()
        marshaller.contextPath = "com.pientaa.gateway.wsdl"
        return marshaller
    }

    @Bean
    fun paymentServiceClient(marshaller: Jaxb2Marshaller?): PaymentServiceClient {
        val client = PaymentServiceClient()
        client.defaultUri = "http://localhost:8080/ws"
        client.marshaller = marshaller
        client.unmarshaller = marshaller
        return client
    }
}