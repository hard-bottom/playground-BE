package com.hardbottom.playground.population

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Population(
    @Id
    val code: Int? = null,
    val time: Int,
    val count: Int,
    @CreationTimestamp
    val create_time: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(null, 0, 0)

    fun toReadPopulationDTO(): ReadPopulationDTO {
        return ReadPopulationDTO(
            code = code,
            time = time,
            count = count,
            create_time = create_time
        )
    }
}

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