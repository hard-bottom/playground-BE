package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationService {
    @Autowired
    lateinit var populationRepository: PopulationRepository

    fun getPopulations(): List<ReadPopulationDTO> {
        val population = populationRepository.findAll()
        return population.map {it.toReadPopulationDTO()}
    }

    fun getPopulationByDistrictCode(code: Int, time: Int): Int {
        val population = populationRepository.findPopulationByCodeAndTime(code, time)
        return population.count
    }
}

@Service
class LocationService {

    @Autowired
    lateinit var locationRepository: LocationRepository

    fun getLocations(): List<ReadLocationDTO> {
        val location = locationRepository.findAll()
        return location.map {it.toReadLocationDTO()}
    }

    fun getDistrictCode(district: String): Int {
        val location = locationRepository.findLocationByDistrict(district)
        return location.code
    }
}