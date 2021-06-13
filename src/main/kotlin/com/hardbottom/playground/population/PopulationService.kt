package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationService {
    @Autowired
    lateinit var populationRepository: PopulationRepository
    @Autowired
    lateinit var locationRepository: LocationRepository

    fun getPopulations(time: Int): List<ReadPopulationDTO> {
        return populationRepository.findAllByTime(time).map { it.toReadPopulationDTO() }
    }

    fun getPopulation(district: String, time: Int): ReadPopulationDTO {
        val location = locationRepository.findLocationByDistrict(district).toReadLocationDTO()
        return populationRepository.findPopulationByCodeAndTime(location.code, time).toReadPopulationDTO()
    }
}
