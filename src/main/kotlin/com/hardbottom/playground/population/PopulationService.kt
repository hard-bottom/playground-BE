package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationService {
    @Autowired
    lateinit var populationRepository: PopulationRepository
    @Autowired
    lateinit var locationRepository: LocationRepository

    // TODO(jyeon.kim) : use model mapper?
    private fun Population.toRetInfo() = RetInfoDTO(
        district = "",
        day = "$day",
        time = "$time",
        count = count,
        city = ""
    )

    fun getPopulations(day: String, time: String): List<RetInfoDTO> {
        return populationRepository.findAllByDayAndTime(day,time).map { it.toRetInfo() }
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
}
