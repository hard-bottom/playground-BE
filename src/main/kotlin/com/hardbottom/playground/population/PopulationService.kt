package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationService {
    @Autowired
    lateinit var populationRepository: PopulationRepository
    @Autowired
    lateinit var locationRepository: LocationRepository

    fun getPopulations(day: String, time: String): List<ReadPopulationDTO> {
        return populationRepository.findAllByDayAndTime(day, time).map { it.toReadPopulationDTO() }
    }

    fun getPopulation(district: String, day: String, time: String): ReadPopulationDTO {
        val location = locationRepository.findLocationByDistrict(district).toReadLocationDTO()
        return populationRepository.findPopulationByCodeAndDayAndTime(location.code, day, time).toReadPopulationDTO()
    }

    fun insertPopulations(data: List<Population>) {
        populationRepository.saveAll(data)
    }

    fun getLocations(): List<ReadLocationDTO> {
        return locationRepository.findAllBy().map { it.toReadLocationDTO() }
    }
}
