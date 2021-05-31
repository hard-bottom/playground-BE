package com.hardbottom.playground.location

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LocationController {

    @Autowired
    private lateinit var locationService: LocationService

    @GetMapping("/locations")
    private fun getPopulations() : ResponseEntity<Any> {
        return ResponseEntity
                .ok()
                .body(locationService.getLocations())
    }

}