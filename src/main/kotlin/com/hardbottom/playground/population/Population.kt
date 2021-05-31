package com.hardbottom.playground.population

import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Population(
    @Id
    val code: Long,
    val time: Int,
    val count: Int,
    @CreationTimestamp
    val create_time: LocalDateTime = LocalDateTime.now()
)
