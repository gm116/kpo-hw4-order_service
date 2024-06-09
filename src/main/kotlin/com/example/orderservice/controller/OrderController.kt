package com.example.orderservice.controller

// OrderController.kt
import com.example.orderservice.model.Order
import com.example.orderservice.model.OrdersRequest
import com.example.orderservice.service.OrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun createOrder(@RequestBody orderRequest: OrdersRequest): ResponseEntity<Order> {
        return try {
            val order = orderService.createOrder(orderRequest.userId, orderRequest.fromStationId, orderRequest.toStationId)
            ResponseEntity.ok(order)
        } catch (e: Exception) {
            ResponseEntity.badRequest().body(null)
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

