package com.hardbottom.playground.population

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Location(
    @Id
    @GeneratedValue
    val id: Long? = null,
    val city: String,
    val district: String,
    val code: String
) {
    constructor() : this(null, "", "", "")

    fun toReadLocationDTO(): ReadLocationDTO {
        return ReadLocationDTO(
            district = district,
            code = code,
            city = city
        )
    }

    override fun toString() = "Location(id=$id, city=$city, district=$district, code=$code)"
}