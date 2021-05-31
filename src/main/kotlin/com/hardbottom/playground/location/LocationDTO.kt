package com.hardbottom.playground.location

data class ReadLocationDTO (
    val id: Long? = null,
    val city: String,
    val district: String,
    val code: Int
)