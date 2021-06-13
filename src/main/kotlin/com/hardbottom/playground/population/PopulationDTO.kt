package com.hardbottom.playground.population

import java.time.LocalDateTime

data class ReadPopulationDTO (
    val code: Int? = null,
    val time: Int,
    val count: Int
)
