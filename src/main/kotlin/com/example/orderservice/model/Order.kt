package com.example.orderservice.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
data class Order(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(name = "user_id")
    val userId: Long,
    @Column(name = "from_station_id")
    val fromStationId: Long,
    @Column(name = "to_station_id")
    val toStationId: Long,
    val status: Int,
    val created: LocalDateTime = LocalDateTime.now()
)
