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
    @Autowired
    private lateinit var locationService: LocationService

    @GetMapping("/populations")
    private fun getPopulations(@RequestParam("city", required=false) city: String,
                               @RequestParam("district") district: String,
                               @RequestParam("time") time: String) : ResponseEntity<Any> {
        val code = locationService.getDistrictCode(district)
        val count = populationService.getPopulationByDistrictCode(code, Integer.parseInt(time))

        return ResponseEntity
                .ok()
                .body(count)
    }

}