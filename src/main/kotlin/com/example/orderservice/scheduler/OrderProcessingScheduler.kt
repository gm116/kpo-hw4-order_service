package com.example.orderservice.scheduler

import com.example.orderservice.service.OrderService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OrderProcessingScheduler(private val orderService: OrderService) {

    @Scheduled(fixedRate = 10000)
    fun processOrders() {
        orderService.processOrders()
    }
}
