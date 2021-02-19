package com.pientaa.soapservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SoapServiceApplication

fun main(args: Array<String>) {
    runApplication<SoapServiceApplication>(*args)
}
