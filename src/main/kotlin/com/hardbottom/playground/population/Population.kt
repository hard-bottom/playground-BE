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
            count = count
        )
    }
}