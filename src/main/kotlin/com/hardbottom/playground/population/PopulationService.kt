package com.hardbottom.playground.population
import com.hardbottom.playground.config.LocationProperty

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PopulationService {
    @Autowired
    lateinit var populationRepository: PopulationRepository
    @Autowired
    lateinit var locationRepository: LocationRepository

    fun getPopulations(day: String, time: String): List<RetInfoDTO> {
        var pops = populationRepository.findAllByDayAndTime(day,time)
        var retList = arrayListOf<RetInfoDTO>()
        for (pop in pops) {
            var districtAndCity = LocationProperty.getDistrictAndCityFromCode(pop.code)
            if (districtAndCity != null) {
                retList.add(
                    RetInfoDTO(
                        districtAndCity.first,
                        pop.day,
                        pop.time,
                        pop.count,
                        districtAndCity.second
                    )
                )
            }
        }
        return retList
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
