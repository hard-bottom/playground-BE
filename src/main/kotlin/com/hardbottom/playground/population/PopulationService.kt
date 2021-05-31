package com.hardbottom.playground.population

import org.springframework.stereotype.Service

@Service
class PopulationService {

    fun getPopulations(): List<Population> {
        val p1 = Population(11110,20210430,10000)
        val p2 = Population(11140,20210440,1000)
        val ret = mutableListOf<Population>(p1, p2)

        return ret
    }
}