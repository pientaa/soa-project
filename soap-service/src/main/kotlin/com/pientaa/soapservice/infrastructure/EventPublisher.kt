package com.pientaa.soapservice.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pientaa.soapservice.config.RabbitConfig.Companion.SOAP_QUEUE
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component

@Component
class EventPublisher(
    private val rabbitTemplate: RabbitTemplate
) {
    companion object {
        private val log = LoggerFactory.getLogger(EventPublisher::class.java)

        val mapper: ObjectMapper
            get() = jacksonObjectMapper().apply {
                registerModule(JavaTimeModule())
                registerKotlinModule()
            }
    }

    fun publish(event: RollbackUserDeleted) {
        val rabbitEvent = RabbitEvent(
            eventLoad = mapper.writeValueAsString(event),
            eventType = "RollbackUserDeleted"
        )
        log.info("Publishing event: $rabbitEvent")
        rabbitTemplate.convertAndSend(SOAP_QUEUE, mapper.writeValueAsString(rabbitEvent))
    }
}