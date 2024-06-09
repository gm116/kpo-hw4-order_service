package com.example.orderservice.model

data class OrdersRequest(
    val userId: Long,

    val fromStationId: Long,

    val toStationId: Long,
)
