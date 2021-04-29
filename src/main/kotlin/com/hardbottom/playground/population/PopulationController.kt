package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PopulationController {

    @Autowired
    private lateinit var populationService: PopulationService

    @GetMapping("/populations")
    private fun getPopulations() : ResponseEntity<Any> {
        return ResponseEntity
                .ok()
                .body(populationService.getPopulations())
    }

}