package com.hardbottom.playground.population

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PopulationController {

    @Autowired
    private lateinit var populationService: PopulationService

    @GetMapping("/population", produces = ["application/json"])
    private fun getPopulation(@RequestParam("city", required=false) city: String,
                               @RequestParam("district") district: String,
                               @RequestParam("time") time: String) : ResponseEntity<Any> {
        println("district: $district")
        println("time: $time")
        return ResponseEntity
                .ok()
                .body(populationService.getPopulation(district, Integer.parseInt(time)))
    }

    @GetMapping("/populations", produces = ["application/json"])
    private fun getPopulations(@RequestParam("city", required=false) city: String,
                               @RequestParam("time") time: String) : ResponseEntity<Any> {
        println("time: $time")
        return ResponseEntity
            .ok()
            .body(populationService.getPopulations(Integer.parseInt(time)))
    }
}
