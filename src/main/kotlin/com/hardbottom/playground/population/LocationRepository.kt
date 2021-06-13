package com.hardbottom.playground.population

import org.springframework.data.repository.CrudRepository

interface LocationRepository: CrudRepository<Location, Long> {
    fun findAllBy(): List<Location>
    fun findLocationByDistrict(district: String): Location
}
