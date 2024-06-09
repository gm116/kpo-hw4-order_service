package com.example.orderservice.repository

import com.example.orderservice.model.Station
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StationRepository : JpaRepository<Station, Long>
