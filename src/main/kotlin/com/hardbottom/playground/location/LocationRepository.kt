package com.hardbottom.playground.location

import org.springframework.data.repository.CrudRepository

interface LocationRepository: CrudRepository<Location, Long> {
    fun findAllBy(): List<Location>
}