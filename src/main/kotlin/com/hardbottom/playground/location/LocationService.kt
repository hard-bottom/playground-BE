package com.hardbottom.playground.location

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class LocationService {

    @Autowired
    lateinit var locationRepository: LocationRepository

    fun getLocations(): List<ReadLocationDTO> {
        val location = locationRepository.findAll()
        return location.map {it.toReadLocationDTO()}
    }
}