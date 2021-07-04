package com.hardbottom.playground.population

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Population(
    @Id
    var id: Long? = null,
    var code: String,
    var day: String,
    var time: String,
    var count: Int,
    @CreationTimestamp
    var create_time: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(null, "", "", "", 0)
    constructor(id: Long, code:String, day:String, time:String, count: Int): this() {
        this.id = id
        this.code = code
        this.day = day
        this.time = time
        this.count = count
    }

    fun toReadPopulationDTO(): ReadPopulationDTO {
        return ReadPopulationDTO(
            code = code,
            day = day,
            time = time,
            count = count
        )
    }
}