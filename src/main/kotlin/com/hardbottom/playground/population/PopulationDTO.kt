package com.hardbottom.playground.population

import java.time.LocalDateTime

data class ReadPopulationDTO (
    val code: String? = null,
    val day: String,
    val time: String,
    val count: Int
)
