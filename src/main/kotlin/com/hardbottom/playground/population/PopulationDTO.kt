package com.hardbottom.playground.population

import java.time.LocalDateTime

data class ReadPopulationDTO (
    val code: Int? = null,
    val time: Int,
    val count: Int,
    val create_time: LocalDateTime
)

data class ReadLocationDTO (
    val id: Long? = null,
    val city: String,
    val district: String,
    val code: Int
)