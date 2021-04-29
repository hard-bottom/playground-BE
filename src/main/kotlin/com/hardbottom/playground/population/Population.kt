package com.hardbottom.playground.population

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Population(
        @Id
        @GeneratedValue
        val id: Long,
        val address1: String,
        val address2: String,
        val count: Int
)
