package com.hardbottom.playground.population

import org.springframework.data.repository.CrudRepository

interface PopulationRepository: CrudRepository<Population, Long> {
    fun findAllBy(): List<Population>
    fun findAllByDayAndTime(day: String, time: String): List<Population>
    fun findPopulationByCodeAndDayAndTime(code: String, day: String, time: String): Population
}
