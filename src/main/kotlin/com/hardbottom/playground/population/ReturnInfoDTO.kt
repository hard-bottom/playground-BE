package com.hardbottom.playground.population

data class RetInfoDTO (
    val district: String,
    val day: String,
    val time: String,
    val count: Int,
    val city: String
) {
    override fun toString() = "RetInfoDTO(district=$district, day=$day, time=$time, count=$count, city=$city)"
}
