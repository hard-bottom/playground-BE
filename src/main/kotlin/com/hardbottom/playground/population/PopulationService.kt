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

    fun getPopulation(district: String, day: String, time: String): RetInfoDTO {
        val location = locationRepository.findLocationByDistrict(district).toReadLocationDTO()
        val population = populationRepository.findPopulationByCodeAndDayAndTime(location.code, day, time).toReadPopulationDTO()
        return RetInfoDTO(location.district, population.day, population.time, population.count, location.city)
    }

    fun insertPopulations(data: List<Population>) {
        populationRepository.saveAll(data)
    }

    fun getLocations(): List<ReadLocationDTO> {
        return locationRepository.findAllBy().map { it.toReadLocationDTO() }
    }

    // TODO(jyeon.kim) : Test function for Setting of ReturnInfoDTO
    /*fun TestConstValueOfPopulation(district: String, day: String, time: String): RetInfoDTO {
        val location = locationRepository.findLocationByDistrict("종로구").toReadLocationDTO()
        val population = populationRepository.findPopulationByCodeAndDayAndTime("11110", "SUNDAY", "00").toReadPopulationDTO()
        println(location.toString())
        println(population.toString())
        return RetInfoDTO(location.district, population.day, population.time, population.count, location.city)
    }*/
}
