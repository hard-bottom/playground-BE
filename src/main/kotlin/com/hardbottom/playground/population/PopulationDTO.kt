package com.hardbottom.playground.population

data class ReadPopulationDTO (
    val code: String? = null,
    val day: String,
    val time: String,
    val count: Int
)