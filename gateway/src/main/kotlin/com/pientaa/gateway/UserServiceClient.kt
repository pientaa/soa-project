package com.pientaa.gateway

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.pientaa.gateway.dto.UserDTO
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class UserServiceClient {
    companion object {
        private val log = LoggerFactory.getLogger(UserServiceClient::class.java)
        val JSON: MediaType = "application/json; charset=utf-8".toMediaType()

        val mapper: ObjectMapper
            get() = jacksonObjectMapper().apply {
                registerModule(JavaTimeModule())
                registerKotlinModule()
            }

        val client = OkHttpClient()
    }

    @Value("\${services.user.url}")
    lateinit var userServiceUrl: String

    fun createUser(createUserDTO: UserDTO) {

        val json = mapper.writeValueAsString(createUserDTO)

        val body: RequestBody = json.toRequestBody(JSON)
        val request: Request = Request.Builder()
            .url(userServiceUrl)
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            log.info("Create user { ${createUserDTO.firstName}, ${createUserDTO.lastName} } request")
            log.info("Client response: ${response.code}, ${response.body?.string()}")
        }
    }

    fun deleteUser(userId: String) {

        val urlBuilder: HttpUrl.Builder = userServiceUrl.toHttpUrlOrNull()!!.newBuilder()
        urlBuilder.addQueryParameter("userId", userId)

        val url: String = urlBuilder.build().toString()

        val request: Request = Request.Builder()
            .url(url)
            .delete()
            .build()

        client.newCall(request).execute().use { response ->
            log.info("Delete user { $userId } request")
            log.info("Client response: ${response.code}, ${response.body?.string()}")
        }
    }

    fun getAllUsers(): List<UserDTO> {
        val request: Request = Request.Builder()
            .url(userServiceUrl)
            .get()
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string()
            log.info("Get all users request")
            log.info("Client response: ${response.code}, $body")
            return body?.let { mapper.readValue<List<UserDTO>>(it) } ?: emptyList()
        }
    }
}