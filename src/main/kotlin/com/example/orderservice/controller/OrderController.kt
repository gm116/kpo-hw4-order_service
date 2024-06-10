package com.example.orderservice.controller

import com.example.orderservice.model.Order
import com.example.orderservice.model.OrdersRequest
import com.example.orderservice.service.OrderService
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/order")
class OrderController(private val orderService: OrderService) {

    @PostMapping("/create")
    fun createOrder(@RequestHeader("Authorization") token: String, @RequestBody orderRequest: OrdersRequest): ResponseEntity<Any> {
        return try {
            val restTemplate = RestTemplate()
            val headers = HttpHeaders().apply {
                set("Authorization", token)
                contentType = MediaType.APPLICATION_JSON
            }

            val requestEntity = HttpEntity(ValidateSessionRequest(token.removePrefix("Bearer ")), headers)
            val responseEntity = restTemplate.postForEntity(
                "http://localhost:8080/auth/validate_session",
                requestEntity,
                ValidateSessionResponse::class.java
            )

            if (responseEntity.statusCode == HttpStatus.OK && responseEntity.body?.valid == true) {
                val order = orderService.createOrder(orderRequest.userId, orderRequest.fromStationId, orderRequest.toStationId)
                ResponseEntity.ok(order)
            } else {
                ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверный токен")
            }
        } catch (e: HttpClientErrorException) {
            ResponseEntity.status(e.statusCode).body("Введите корректный токен или тело запроса")
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error: ${e.message}")
        }
    }
    @GetMapping("/{id}")
    fun getOrderById(@PathVariable id: Long): ResponseEntity<Order> {
        return try {
            val order = orderService.getOrderById(id)
            ResponseEntity.ok(order)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}

data class ValidateSessionRequest(val token: String)
data class ValidateSessionResponse(val valid: Boolean, val userId: Long?)

