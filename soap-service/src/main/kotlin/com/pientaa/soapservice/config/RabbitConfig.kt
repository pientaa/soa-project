package com.pientaa.soapservice.config

import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {
    companion object {
        const val REST_QUEUE = "rest_queue"
        const val SOAP_QUEUE = "soap_queue"
    }

    @Bean
    fun soapQueue(): Queue = QueueBuilder.durable(SOAP_QUEUE)
        .withArgument(
            "x-dead-letter-exchange",
            "dead_letter_exchange"
        )
        .withArgument(
            "x-dead-letter-routing-key",
            "dead_letter_queue"
        )
        .build()

    @Bean
    fun restQueue(): Queue = QueueBuilder.durable(REST_QUEUE)
        .withArgument(
            "x-dead-letter-exchange",
            "dead_letter_exchange"
        )
        .withArgument(
            "x-dead-letter-routing-key",
            "dead_letter_queue"
        )
        .build()
}