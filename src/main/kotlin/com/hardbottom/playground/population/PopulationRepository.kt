package com.hardbottom.playground.population

import org.springframework.data.repository.CrudRepository

interface PopulationRepository: CrudRepository<Population, Long> {
    fun findAllBy(): List<Population>
    fun findAllByTime(time: Int): List<Population>
    fun findPopulationByCodeAndTime(code: Int, time: Int): Population
}
