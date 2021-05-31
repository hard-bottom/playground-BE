package com.hardbottom.playground.location

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
    val code: Int
) {
    constructor() : this(null, "", "", 0)

    fun toReadLocationDTO(): ReadLocationDTO {
        return ReadLocationDTO(
            id = id,
            city = city,
            district = district,
            code = code
        )
    }
}
