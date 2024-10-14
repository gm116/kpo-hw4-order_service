package com.example.orderservice.config

import com.example.orderservice.model.Station
import com.example.orderservice.repository.StationRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataInitializer {

    @Bean
    fun init(stationRepository: StationRepository) = CommandLineRunner {
        // Проверяем, если таблица пустая
        if (stationRepository.count() == 0L) {
            val stations = listOf(
                Station(name = "Москва"),
                Station(name = "Казань"),
                Station(name = "Санкт-Петербург"),
                Station(name = "Набережные Челны")
            )
            stationRepository.saveAll(stations)
        }
    }
}

