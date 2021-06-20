package com.hardbottom.playground.schedule

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.getForObject

@Component
class PopulationInfoCollector {

    @Autowired
    lateinit var restTemplateBuilder: RestTemplateBuilder

    @Scheduled(cron = "10 * * * * *")
    fun getPopulationInfo() {
        val restTemplate = restTemplateBuilder.build()

        val date = "20200617"
        val datakey = "76427379726a736734374741684569"

        println("Start get pop")
        val result = restTemplate.getForObject(
            "http://openapi.seoul.go.kr:8088/{datakey}/xml/SPOP_LOCAL_RESD_DONG/1/5/{date}",
            String::class.java,
            datakey,
            date
        );

        println(result);

    }
}