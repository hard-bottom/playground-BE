package com.hardbottom.playground.population

import org.springframework.stereotype.Service

@Service
class PopulationService {

    fun getPopulations(): List<Population> {
        val p1 = Population(1,"영통구","이의동",1000)
        val p2 = Population(2,"영통구","하동",600)
        val ret = mutableListOf<Population>(p1, p2)

        return ret
    }
}