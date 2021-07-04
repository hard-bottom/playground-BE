package com.hardbottom.playground.JachiAPI

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class JachiDataObject(
    var spop_local_resd_jachi: JachiData
)

data class JachiData(
    val list_total_count: Long,
    val result: JachiDataResult,
    val row: List<JachiDataRow>
)

data class JachiDataResult(
    val code: String,
    val message: String
)

data class JachiDataRow (
    var stdr_de_id: String,
    var tmzon_pd_se: String,
    var adstrd_code_se: String,
    var tot_lvpop_co: String,
    var male_f0t9_lvpop_co: String,
    var male_f10t14_lvpop_co: String,
    var male_f15t19_lvpop_co: String,
    var male_f20t24_lvpop_co: String,
    var male_f25t29_lvpop_co: String,
    var male_f30t34_lvpop_co: String,
    var male_f35t39_lvpop_co: String,
    var male_f40t44_lvpop_co: String,
    var male_f45t49_lvpop_co: String,
    var male_f50t54_lvpop_co: String,
    var male_f55t59_lvpop_co: String,
    var male_f60t64_lvpop_co: String,
    var male_f65t69_lvpop_co: String,
    var male_f70t74_lvpop_co: String,
    var female_f0t9_lvpop_co: String,
    var female_f10t14_lvpop_co: String,
    var female_f15t19_lvpop_co: String,
    var female_f20t24_lvpop_co: String,
    var female_f25t29_lvpop_co: String,
    var female_f30t34_lvpop_co: String,
    var female_f35t39_lvpop_co: String,
    var female_f40t44_lvpop_co: String,
    var female_f45t49_lvpop_co: String,
    var female_f50t54_lvpop_co: String,
    var female_f55t59_lvpop_co: String,
    var female_f60t64_lvpop_co: String,
    var female_f65t69_lvpop_co: String,
    var female_f70t74_lvpop_co: String
)