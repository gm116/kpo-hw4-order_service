package com.example.orderservice.service

import com.example.orderservice.model.Order
import com.example.orderservice.repository.OrderRepository
import com.example.orderservice.repository.StationRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val stationRepository: StationRepository
) {
    fun createOrder(userId: Long, fromStationId: Long, toStationId: Long): Order {
        val fromStation = stationRepository.findById(fromStationId).orElseThrow { Exception("Направление \"ОТКУДА\" не найдено") }
        val toStation = stationRepository.findById(toStationId).orElseThrow { Exception("Направление \"КУДА\" не найдено") }
        val order = Order(
            userId = userId,
            fromStationId = fromStation.id,
            toStationId = toStation.id,
            status = 1,
            created = LocalDateTime.now()
        )
        return orderRepository.save(order)
    }

    fun getOrderById(id: Long): Order {
        return orderRepository.findById(id).orElseThrow { Exception("Заказ не найден") }
    }

    fun processOrders() {
        val orders = orderRepository.findByStatus(1)
        for (order in orders) {
            val newStatus = if (Math.random() > 0.5) 2 else 3
            val updatedOrder = order.copy(status = newStatus)
            orderRepository.save(updatedOrder)
        }
    }
}

