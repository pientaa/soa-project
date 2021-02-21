package com.pientaa.restservice.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pientaa.restservice.UserService
import com.pientaa.restservice.config.RabbitConfig.Companion.SOAP_QUEUE
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

@Component
@RabbitListener(queues = [SOAP_QUEUE])
class EventListener(private val userService: UserService) {
    companion object {
        val mapper: ObjectMapper
            get() = jacksonObjectMapper().apply {
                registerModule(JavaTimeModule())
                registerKotlinModule()
            }
    }

    @RabbitHandler
    fun handle(message: String) {
        val rabbitEvent: RabbitEvent = mapper.readValue(message)

//        val actualEvent = when (rabbitEvent.eventType) {
//            TODO()
//            else -> throw IllegalStateException()
//        }
//        handle(actualEvent)
    }
}