package com.pientaa.restservice.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pientaa.restservice.UserService
import com.pientaa.restservice.config.RabbitConfig.Companion.SOAP_QUEUE
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@RabbitListener(queues = [SOAP_QUEUE])
class EventListener(private val userService: UserService) {
    companion object {
        private val log = LoggerFactory.getLogger(EventListener::class.java)

        val mapper: ObjectMapper
            get() = jacksonObjectMapper().apply {
                registerModule(JavaTimeModule())
                registerKotlinModule()
            }
    }

    @RabbitHandler
    fun handle(message: String) {
        val rabbitEvent: RabbitEvent = mapper.readValue(message)

        log.info("Received event: $rabbitEvent")


        when (rabbitEvent.eventType) {
            "RollbackUserDeleted" -> userService.handle(mapper.readValue(rabbitEvent.eventLoad) as RollbackUserDeleted)
            else -> throw IllegalStateException()
        }
    }
}